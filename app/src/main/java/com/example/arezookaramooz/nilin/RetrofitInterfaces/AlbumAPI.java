package com.example.arezookaramooz.nilin.RetrofitInterfaces;

import com.example.arezookaramooz.nilin.Data.Album;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface AlbumAPI {

    @GET("/albums")
    Call<ArrayList<Album>> getAlbums();

}
