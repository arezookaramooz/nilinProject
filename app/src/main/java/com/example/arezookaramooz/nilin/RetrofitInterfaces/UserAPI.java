package com.example.arezookaramooz.nilin.RetrofitInterfaces;

import com.example.arezookaramooz.nilin.Data.User;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface UserAPI {

    @GET("/users")
    Call<ArrayList<User>> getUsers();
}
