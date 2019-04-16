package com.example.arezookaramooz.nilin;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;

import com.example.arezookaramooz.nilin.Data.AlbumManager;
import com.example.arezookaramooz.nilin.Data.Photo;
import com.example.arezookaramooz.nilin.RetrofitInterfaces.PhotoAPI;
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

public class PhotosNetworkClient implements Callback<ArrayList<Photo>> {
    static final String BASE_URL = "https://jsonplaceholder.typicode.com/";
    private static final String TAG = "AlbumsNetworkClient";
    Context context;
    PhotosAdapter photosAdapter;
    int albumId;
    private ProgressDialog progressDialog;

    public PhotosNetworkClient(Context context, PhotosAdapter photosAdapter, int albumId) {
        this.context = context;
        this.photosAdapter = photosAdapter;
        this.albumId = albumId;
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

        PhotoAPI albumAPI = retrofit.create(PhotoAPI.class);

        Call<ArrayList<Photo>> call = albumAPI.getPhotos(albumId);
        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<ArrayList<Photo>> call, Response<ArrayList<Photo>> response) {
        progressDialog.dismiss();
        if (response.isSuccessful()) {
            ArrayList<Photo> photosList = response.body();
            for (int i = 0; i < photosList.size(); i++) {
                if (photosList.get(i).getAlbumId() == albumId) {
                    photosAdapter.photos.add(photosList.get(i));
                }
            }
//        AlbumManager albumManager = AlbumManager.getInstance(context);
//        albumManager.addAlbums(albumsList);
            photosAdapter.notifyDataSetChanged();
        } else {
            Log.d(TAG, "error in response");
        }
    }

    @Override
    public void onFailure(Call<ArrayList<Photo>> call, Throwable t) {
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

