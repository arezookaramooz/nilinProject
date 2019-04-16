package com.example.arezookaramooz.nilin;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {

    private AlbumAdapter adapter;
    private RecyclerView recyclerView;
    private SwipeRefreshLayout mySwipeRefreshLayout;
//    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_albums);

        mySwipeRefreshLayout = findViewById(R.id.swipeToRefresh);
        recyclerView = findViewById(R.id.albums_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new AlbumAdapter(this);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(adapter);

        mySwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

//                new DownloadAlbumTask().execute("https://jsonplaceholder.typicode.com/albums");
                AlbumsNetworkClient albumsNetworkClient = new AlbumsNetworkClient(MainActivity.this, adapter);
                albumsNetworkClient.start();
//                new DownloadUserNameTask().execute("https://jsonplaceholder.typicode.com/users");
                UsersNetworkClient usersNetworkClient = new UsersNetworkClient(MainActivity.this, adapter);
                usersNetworkClient.start();

                mySwipeRefreshLayout.setRefreshing(false);
            }
        });

//        new DownloadAlbumTask().execute("https://jsonplaceholder.typicode.com/albums");
        AlbumsNetworkClient albumsNetworkClient = new AlbumsNetworkClient(MainActivity.this, adapter);
        albumsNetworkClient.start();
//        new DownloadUserNameTask().execute("https://jsonplaceholder.typicode.com/users");
        UsersNetworkClient usersNetworkClient = new UsersNetworkClient(MainActivity.this, adapter);
        usersNetworkClient.start();
    }


    @Override
    protected void onResume() {

        super.onResume();
        adapter.notifyDataSetChanged();
    }

//    private class DownloadAlbumTask extends AsyncTask<String, String, String> {
//
//
//        public DownloadAlbumTask() {
//            progressDialog = new ProgressDialog(MainActivity.this);
//        }
//
//        @Override
//        protected void onPreExecute() {
//            showProgressDialog();
//        }
//
//        @Override
//        protected String doInBackground(String... urls) {
//            return download(urls);
//        }
//
//        @Override
//        protected void onPostExecute(String s) {
//            super.onPostExecute(s);
//
//            progressDialog.dismiss();
//
//            if (s == null) {
//                showAlertDialog();
//            } else {
//                parsAlbums(s);
//            }
//        }
//    }

//    private class DownloadUserNameTask extends AsyncTask<String, String, String> {
//        @Override
//        protected String doInBackground(String... urls) {
//            return download(urls);
//        }
//
//        @Override
//        protected void onPostExecute(String s) {
//            super.onPostExecute(s);
//
//            if (s == null) {
//            } else {
//
//                parsUsers(s);
//            }
//        }
//    }

//    private String download(String... urls) {
//        OkHttpClient client = new OkHttpClient();
//        Request request =
//                new Request.Builder()
//                        .url(urls[0])
//                        .build();
//        Response response = null;
//        try {
//            response = client.newCall(request).execute();
//            if (response.isSuccessful()) {
//                return response.body().string();
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }

//    private void showAlertDialog() {
//
//        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
//        builder.setTitle("ERROR !!");
//        builder.setMessage("Sorry there was an error getting data from the Internet.");
//        builder.setCancelable(false)
//                .setPositiveButton("Retry", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int n) {
//                        dialog.dismiss();
//                        new DownloadAlbumTask().execute("https://jsonplaceholder.typicode.com/albums");
//                    }
//                });
//        AlertDialog alert = builder.create();
//        alert.show();
//    }

//    private void parsAlbums(String s) {
//        ArrayList<Album> albums;
//        Type listType = new TypeToken<ArrayList<Album>>() {
//        }.getType();
//        albums = new Gson().fromJson(s, listType);
//        AlbumManager m = AlbumManager.getInstance(MainActivity.this);
//        m.addAlbums(albums);
//        adapter.notifyDataSetChanged();
//    }

//    private void parsUsers(String s) {
//        Type listType = new TypeToken<ArrayList<User>>() {
//        }.getType();
//        ArrayList<User> users;
//        users = new Gson().fromJson(s, listType);
//        UserManager m = UserManager.getInstance(MainActivity.this);
//        m.addUsers(users);
//        adapter.notifyDataSetChanged();
//    }

//    private void showProgressDialog() {
//        progressDialog.setMessage("Downloading albums...");
//        progressDialog.setIndeterminate(true);
//        progressDialog.show();
//    }
}