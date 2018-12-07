package com.example.arezookaramooz.nilin;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.arezookaramooz.nilin.Data.Album;
import com.example.arezookaramooz.nilin.Data.AlbumManager;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

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
    }


    @Override
    protected void onResume() {
        super.onResume();
        adapter.notifyDataSetChanged();
    }

    private class DownloadAlbumTask extends AsyncTask<String, String, String> {
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
                Toast t = Toast.makeText(MainActivity.this, "error in connecting with server", Toast.LENGTH_SHORT);
                t.show();
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
}

