package com.redteam.workforday.retrofit;

import com.redteam.workforday.models.User;
import com.redteam.workforday.models.WorkApplication;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.http.Body;
import retrofit2.http.GET;
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
}
