//package com.example.arezookaramooz.nilin;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.support.v7.app.AppCompatActivity;
//import android.support.v7.widget.DividerItemDecoration;
//import android.support.v7.widget.LinearLayoutManager;
//import android.support.v7.widget.RecyclerView;
//import android.util.Log;
//
//import com.example.arezookaramooz.nilin.Data.Album;
//import com.example.arezookaramooz.nilin.Data.AlbumManager;
//
//public class PhotosActivity extends AppCompatActivity {
//
//
//    PhotosAdapter adapter;
//    int albumPosition;
//
//    private RecyclerView recyclerView;
//    AlbumManager m = AlbumManager.getInstance(this);
//    Album album;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_photos);
//
//
//
//        Intent mIntent = getIntent();
//        albumPosition = mIntent.getIntExtra("position", 0);
//
//
//        recyclerView = (RecyclerView) findViewById(R.id.photos_recycler_view);
//
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        adapter = new PhotosAdapter(this, albumPosition);
//        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
//        recyclerView.setAdapter(adapter);
//
//
//
//        Log.d("PhotosActivity", "position is:" + albumPosition);
//
////        album = m.getAlbums().get(position);
//
////        Log.d("PhotosActivity", "link is: " + album.getPhotoLink());
//
//
//
////        ImageView imageView = (ImageView) findViewById(R.id.image_view);
////        Picasso.with(this).load(album.getPhotoLink()).into(imageView);
//
//    }
//
//
//    @Override
//    protected void onResume() {
//        super.onResume();
//        adapter.notifyDataSetChanged();
//    }
//
//}
//
