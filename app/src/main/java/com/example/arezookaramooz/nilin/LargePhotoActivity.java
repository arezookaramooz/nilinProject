package com.example.arezookaramooz.nilin;

import android.Manifest;
import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.arezookaramooz.nilin.Data.AlbumManager;
import com.squareup.picasso.Picasso;

import java.io.File;

public class LargePhotoActivity extends AppCompatActivity {
    public static final int REQUEST_CODE = 2;

    private AlbumManager m = AlbumManager.getInstance(this);
    private static final String DIR_NAME = "nilin";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_large_photo);
        getSupportActionBar().hide();

        Intent myIntent = getIntent();
        final String photoUrl = myIntent.getStringExtra("photoUrl");

        ImageView imageView = findViewById(R.id.large_photo_icon);
        Picasso.with(this).load(photoUrl).into(imageView);

        ImageButton downloadButton = findViewById(R.id.download_button);
        downloadButton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {

                ActivityCompat.requestPermissions(LargePhotoActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_CODE);

                if (checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                    String downloadUrlOfImage = photoUrl;
                    downLoadToGallery(downloadUrlOfImage);
                }
            }
        });
    }

    private void downLoadToGallery(String downloadUrlOfImage) {
        String filename = "filename.jpg";
        File direct =
                new File(Environment
                        .getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
                        .getAbsolutePath() + "/" + DIR_NAME + "/");

        if (!direct.exists()) {
            direct.mkdir();
        }
        DownloadManager dm = (DownloadManager) getApplicationContext().getSystemService(Context.DOWNLOAD_SERVICE);
        Uri downloadUri = Uri.parse(downloadUrlOfImage);
        DownloadManager.Request request = new DownloadManager.Request(downloadUri);
        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI | DownloadManager.Request.NETWORK_MOBILE)
                .setAllowedOverRoaming(false)
                .setTitle(filename)
                .setMimeType("image/jpeg")
                .setDestinationInExternalPublicDir(Environment.DIRECTORY_PICTURES,
                        File.separator + DIR_NAME + File.separator + filename);
        dm.enqueue(request);
        Toast toast = Toast.makeText(LargePhotoActivity.this, "Downloaded to gallery", Toast.LENGTH_SHORT);
        toast.show();
    }
}