package com.example.arezookaramooz.nilin.RetrofitInterfaces;

import com.example.arezookaramooz.nilin.Data.Album;
import com.example.arezookaramooz.nilin.Data.Photo;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface PhotoAPI {

    @GET("/photos?albumId")
    Call<ArrayList<Photo>> getPhotos(@Query("albumId") int albumId);

}
