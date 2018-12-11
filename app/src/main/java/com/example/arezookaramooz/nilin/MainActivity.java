package com.example.arezookaramooz.nilin;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.arezookaramooz.nilin.Data.Album;
import com.example.arezookaramooz.nilin.Data.AlbumManager;
import com.example.arezookaramooz.nilin.Data.User;
import com.example.arezookaramooz.nilin.Data.UserManager;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    AlbumAdapter adapter;

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_albums);

        recyclerView = (RecyclerView) findViewById(R.id.albums_recycler_view);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new AlbumAdapter(this);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(adapter);

        new DownloadAlbumTask().execute("https://jsonplaceholder.typicode.com/albums");

        new DownloadUserNameTask().execute("https://jsonplaceholder.typicode.com/users");
    }


    @Override
    protected void onResume() {
        super.onResume();
        adapter.notifyDataSetChanged();
    }

    private class DownloadAlbumTask extends AsyncTask<String, String, String> {

        private ProgressDialog progressDialog;

        public DownloadAlbumTask() {
            progressDialog = new ProgressDialog(MainActivity.this);
        }

        @Override
        protected void onPreExecute() {
            progressDialog.setMessage("Downloading albums...");
            progressDialog.setIndeterminate(true);
            progressDialog.show();
        }

        @Override
        protected String doInBackground(String... urls) {
            OkHttpClient client = new OkHttpClient();
            Request request =
                    new Request.Builder()
                            .url(urls[0])
                            .build();
            Response response = null;
            try {
                response = client.newCall(request).execute();
                if (response.isSuccessful()) {
                    return response.body().string();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;

        }



        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            progressDialog.dismiss();

            if (s == null) {

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("ERROR !!");
                builder.setMessage("Sorry there was an error getting data from the Internet.");

                builder.setCancelable(false)
                        .setPositiveButton("Retry", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int n) {
                                dialog.dismiss();
                                new  DownloadAlbumTask().execute("https://jsonplaceholder.typicode.com/albums");
                            }
                        });
                AlertDialog alert = builder.create();
                alert.show();

            } else {
                ArrayList<Album> albums;
                Type listType = new TypeToken<ArrayList<Album>>() {
                }.getType();
                albums = new Gson().fromJson(s, listType);
                AlbumManager m = AlbumManager.getInstance(MainActivity.this);
                m.addAlbums(albums);
                adapter.notifyDataSetChanged();
            }
        }
    }

    private class DownloadUserNameTask extends AsyncTask<String, String, String> {
        @Override
        protected String doInBackground(String... urls) {
            OkHttpClient client = new OkHttpClient();
            Request request =
                    new Request.Builder()
                            .url(urls[0])
                            .build();
            Response response = null;
            try {
                response = client.newCall(request).execute();
                if (response.isSuccessful()) {
                    return response.body().string();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;

        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            if (s == null) {
//                Toast t = Toast.makeText(MainActivity.this, "error in connecting with server", Toast.LENGTH_SHORT);
//                t.show();

            } else {

                Type listType = new TypeToken<ArrayList<User>>() {
                }.getType();

                ArrayList<User> users;
                users = new Gson().fromJson(s, listType);

                UserManager m = UserManager.getInstance(MainActivity.this);

                m.addUsers(users);

//                adapter.users.addAll(users);

//                for (int i = 0 ; i < photos.size() ; i++ {
//
//                    if (photos.get(i).getAlbumId() == albumId){
//
//                        adapter.photos.add(photos.get(i));
//                    }
//                }
                adapter.notifyDataSetChanged();
            }
        }
    }

}

