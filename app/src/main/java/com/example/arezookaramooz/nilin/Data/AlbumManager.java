package com.example.arezookaramooz.nilin.Data;

import android.content.Context;

import java.util.ArrayList;

public class AlbumManager {

    private static AlbumManager instance;

    private ArrayList<Album> albums = new ArrayList<Album>();

    private AlbumManager(Context context) {
        Album a1 = new Album("album1", "https://www.babycenter.com/ims/2016/11/580x314xFull-Res-063_wide.jpg.pagespeed.ic.2lgng2RD9_.jpg");
        albums.add(a1);

        Album a2 = new Album("album2", "http://www.pbcexpo.com.au/assets/Exhibitor-logos/8079/_resampled/CroppedFocusedImageWzEyMDAsNjMwLCJ5Iiw1XQ/Parenting-Insights-Hero-Image.jpg");
        albums.add(a2);

    }

    public static AlbumManager getInstance(Context context) {
        if (instance == null)
            instance = new AlbumManager(context);
        return instance;
    }

    public void addAlbum(Album album) {
        albums.add(album);
    }


    public ArrayList<Album> getAlbums() {
        return albums;
    }
}
