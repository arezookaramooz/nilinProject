package com.example.arezookaramooz.nilin;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;

import com.example.arezookaramooz.nilin.Data.Album;
import com.example.arezookaramooz.nilin.Data.AlbumManager;
import com.example.arezookaramooz.nilin.RetrofitInterfaces.AlbumAPI;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AlbumsNetworkClient implements Callback<ArrayList<Album>> {
    static final String BASE_URL = "https://jsonplaceholder.typicode.com/";
    private static final String TAG = "AlbumsNetworkClient";
    Context context;
    AlbumAdapter adapter;
    private ProgressDialog progressDialog;

    public AlbumsNetworkClient(Context context, AlbumAdapter adapter) {
        this.context = context;
        this.adapter = adapter;
    }

    public void start() {

        progressDialog = new ProgressDialog(context);
        showProgressDialog();

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        AlbumAPI albumAPI = retrofit.create(AlbumAPI.class);

        Call<ArrayList<Album>> call = albumAPI.getAlbums();
        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<ArrayList<Album>> call, Response<ArrayList<Album>> response) {
        progressDialog.dismiss();
        if (response.isSuccessful()) {
            ArrayList<Album> albumsList = response.body();
            AlbumManager albumManager = AlbumManager.getInstance(context);
            albumManager.addAlbums(albumsList);
            adapter.notifyDataSetChanged();
        } else {
            Log.d(TAG, "error in response");
        }
    }

    @Override
    public void onFailure(Call<ArrayList<Album>> call, Throwable t) {
        progressDialog.dismiss();
        showAlertDialog();
        t.printStackTrace();
        Log.d(TAG, "response failed");
    }

    private void showProgressDialog() {
        progressDialog.setMessage("Downloading albums...");
        progressDialog.setIndeterminate(true);
        progressDialog.show();
    }

    private void showAlertDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("ERROR !!");
        builder.setMessage("Sorry there was an error getting data from the Internet.");
        builder.setCancelable(false)
                .setPositiveButton("Retry", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int n) {
                        dialog.dismiss();
                        start();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }
}
