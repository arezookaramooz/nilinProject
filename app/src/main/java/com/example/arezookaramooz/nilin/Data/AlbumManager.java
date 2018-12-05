package com.example.arezookaramooz.nilin.Data;

import android.content.Context;

import java.util.ArrayList;

public class AlbumManager {

    private static AlbumManager instance;

    private ArrayList<Album> albums = new ArrayList<Album>();


    private AlbumManager(Context context) {
        ArrayList<String> photoList1 = new ArrayList<String>();
        photoList1.add("https://www.babycenter.com/ims/2016/11/580x314xFull-Res-063_wide.jpg.pagespeed.ic.2lgng2RD9_.jpg");
        photoList1.add("https://d1lhri34tovdcj.cloudfront.net/prod/mom365/featured-images/istock_92000751_small-aaf316e2-ae81-42dc-8e89-c79d600dbb89.jpg");
        photoList1.add("https://www.nwcommonaction.org/wp-content/uploads/2017/12/dressig-baby-boy.jpg");

        ArrayList<String> photoList2 = new ArrayList<String>();
        photoList2.add("http://www.pbcexpo.com.au/assets/Exhibitor-logos/8079/_resampled/CroppedFocusedImageWzEyMDAsNjMwLCJ5Iiw1XQ/Parenting-Insights-Hero-Image.jpg");
        photoList2.add("http://www.mykidsite.com/wp-content/uploads/2015/09/Barbie-Baby-Girl-MK12309.jpg");
        photoList2.add("http://goodheartsshop.com/wp-content/uploads/2015/09/haute_baby_girl1.jpg");
        photoList2.add("https://theadorablebaby.com/wp-content/uploads/2017/07/5.-Cute-princess-baby-girl.jpg");

        Album a1 = new Album("babyBoysAlbum", photoList1);
        albums.add(a1);

        Album a2 = new Album("babyGirlsAlbum", photoList2);
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
