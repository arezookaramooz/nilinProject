package com.example.arezookaramooz.nilin;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.ClipData;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.example.arezookaramooz.nilin.Data.Photo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class PhotosActivity extends AppCompatActivity {

    private PhotosAdapter adapter;
    private int albumId;
    private DividerItemDecoration divider;
    private RecyclerView recyclerView;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private boolean isList = true;
    private ProgressDialog progressDialog;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mymenu, menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        return changeView(item);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photos);

        Intent mIntent = getIntent();
        albumId = mIntent.getIntExtra("albumId", 0);

        mSwipeRefreshLayout = findViewById(R.id.swipeToRefresh1);
        recyclerView = (RecyclerView) findViewById(R.id.photos_recycler_view);
        divider = new DividerItemDecoration(this, LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(divider);
        adapter = new PhotosAdapter(this, albumId);
        recyclerView.setAdapter(adapter);

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                new DownloadPhotosTask().execute("https://jsonplaceholder.typicode.com/photos?albumId=" + albumId);
                mSwipeRefreshLayout.setRefreshing(false);
            }
        });
        new DownloadPhotosTask().execute("https://jsonplaceholder.typicode.com/photos?albumId=" + albumId);
    }

    @Override
    protected void onResume() {
        super.onResume();
        adapter.notifyDataSetChanged();
    }

    private class DownloadPhotosTask extends AsyncTask<String, String, String> {

        public DownloadPhotosTask() {
            progressDialog = new ProgressDialog(PhotosActivity.this);
        }

        @Override
        protected void onPreExecute() {
            showProgressDialog();
        }

        @Override
        protected String doInBackground(String... urls) {

            return download(urls);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            progressDialog.dismiss();

            if (s == null) {
                showAlertDialog(s);
            } else {
                parsPhotos(s);
            }
        }
    }

    private boolean changeView(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.mybutton) {
            if (isList) {
                item.setIcon(R.drawable.grid);
                isList = false;
                recyclerView.removeItemDecoration(divider);
                recyclerView.setLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL));
            } else {
                item.setIcon(R.drawable.list);
                isList = true;
                recyclerView.setLayoutManager(new LinearLayoutManager(this));
                recyclerView.addItemDecoration(divider);
            }
        }
        return super.onOptionsItemSelected(item);
    }

    private void showProgressDialog() {
        progressDialog.setMessage("Downloading photos...");
        progressDialog.setIndeterminate(true);
        progressDialog.show();
    }

    private String download(String... urls) {
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

    private void showAlertDialog(String s) {
        AlertDialog.Builder builder = new AlertDialog.Builder(PhotosActivity.this);
        builder.setTitle("ERROR !!");
        builder.setMessage("Sorry there was an error getting data from the Internet.");

        builder.setCancelable(false)
                .setPositiveButton("Retry", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int n) {
                        dialog.dismiss();
                        new DownloadPhotosTask().execute("https://jsonplaceholder.typicode.com/photos");
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }

    private void parsPhotos(String s) {
        Type listType = new TypeToken<ArrayList<Photo>>() {
        }.getType();
        ArrayList<Photo> photos;
        photos = new Gson().fromJson(s, listType);
        Log.d("PhotosActivity", "albumId is:" + albumId);

        for (int i = 0; i < photos.size(); i++) {

            if (photos.get(i).getAlbumId() == albumId) {
                adapter.photos.add(photos.get(i));
            }
        }
        adapter.notifyDataSetChanged();
    }

}