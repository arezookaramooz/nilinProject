package com.example.arezookaramooz.nilin.Data;

import android.content.Context;

import java.util.ArrayList;

public class AlbumManager {

    private static AlbumManager instance;
    private ArrayList<Album> albums = new ArrayList<Album>();

    private AlbumManager(Context context) {
    }

    public static AlbumManager getInstance(Context context) {
        if (instance == null)
            instance = new AlbumManager(context);
        return instance;
    }

    public ArrayList<Album> getAlbums() {
        return albums;
    }

    public void addAlbums(ArrayList<Album> arrayList) {
        albums.addAll(arrayList);
    }
}
