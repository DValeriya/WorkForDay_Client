package com.example.workforday.retrofit;

import com.example.workforday.models.WorkApplication;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WorkApplicationREST {

    @GET("workerapplication/getapplications")
    Call<List<WorkApplication>> getWorkApplications(@Query("page") int page, @Query("results") int results);
}
