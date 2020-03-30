package com.kel1.kouveepetshop.Api;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

import com.kel1.kouveepetshop.DAO.adminDAO;

public interface ApiInterface {
    //USER-api
    @POST("user/create.php")
    Call<adminDAO> createUser(@Body adminDAO body);

    @POST("user/delete.php")

    Call<adminDAO> deleteUser(@Body adminDAO body);

    @GET("user/login.php")
    Call<adminDAO> loginUser(@Query("username") String username);

}

