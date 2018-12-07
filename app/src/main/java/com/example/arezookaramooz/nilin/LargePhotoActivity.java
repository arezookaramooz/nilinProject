//package com.example.arezookaramooz.nilin;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.support.annotation.Nullable;
//import android.support.v7.app.AppCompatActivity;
//import android.util.Log;
//import android.widget.ImageView;
//
//import com.example.arezookaramooz.nilin.Data.AlbumManager;
//import com.squareup.picasso.Picasso;
//
//public class LargePhotoActivity extends AppCompatActivity {
//
//    AlbumManager m = AlbumManager.getInstance(this);
//
//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_large_photo);
//
//        Intent myIntent = getIntent();
//
//        int albumPosition = myIntent.getIntExtra("albumPosition", 0);
//        int photoPosition = myIntent.getIntExtra("photoPosition", 0);
//
//        ImageView imageView = (ImageView)findViewById(R.id.large_photo_icon);
//
//        Log.d("LargePhotoActivity", "albumPosition is: " + albumPosition + " & photoPositon is " + photoPosition);
//
//        Picasso.with(this).load(m.getAlbums().get(albumPosition).getPhotoLinks().get(photoPosition)).into(imageView);
//
//    }
//}
