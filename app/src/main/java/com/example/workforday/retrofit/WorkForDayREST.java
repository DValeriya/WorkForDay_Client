package com.example.workforday.retrofit;

import com.example.workforday.models.User;
import com.example.workforday.models.WorkApplication;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface WorkForDayREST {

    @GET("workerapplication/getapplications")
    Call<List<WorkApplication>> getWorkApplications(@Query("page") int page, @Query("results") int results);

    @POST("workerapplication/add")
    @Headers("Content-Type: application/json")
    Call<Void> addWorkApplication(@Body WorkApplication workApplication);

    @POST("user/register")
    @Headers("Content-Type: application/json")
    Call<User> saveNewUser(@Body User user);

    @GET("user/get")
    Call<User> singIn(@Header("Authorization") String authorization);
}
