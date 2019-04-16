package com.example.arezookaramooz.nilin;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;

import com.example.arezookaramooz.nilin.Data.User;
import com.example.arezookaramooz.nilin.Data.UserManager;
import com.example.arezookaramooz.nilin.RetrofitInterfaces.UserAPI;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UsersNetworkClient implements Callback<ArrayList<User>> {
    static final String BASE_URL = "https://jsonplaceholder.typicode.com/";
    private static final String TAG = "UsersNetworkClient" ;
    Context context;
    AlbumAdapter adapter;

    public UsersNetworkClient(Context context, AlbumAdapter adapter){
        this.context = context;
        this.adapter = adapter;
    }

    public void start() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        UserAPI userAPI = retrofit.create(UserAPI.class);

        Call<ArrayList<User>> call = userAPI.getUsers();
        call.enqueue(this);

    }

    @Override
    public void onResponse(Call<ArrayList<User>> call, Response<ArrayList<User>> response) {
        if(response.isSuccessful()) {
            ArrayList<User> userssList = response.body();
            UserManager userManager = UserManager.getInstance(context);
            userManager.addUsers(userssList);
            adapter.notifyDataSetChanged();
        } else {
            Log.d(TAG, "error in response");
        }
    }

    @Override
    public void onFailure(Call<ArrayList<User>> call, Throwable t) {
        t.printStackTrace();
        Log.d(TAG, "response failed");
    }
}

