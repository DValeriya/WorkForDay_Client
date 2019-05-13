package com.example.workforday.retrofit;

import android.content.Context;

import com.example.workforday.R;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WorkForDayAPI {
    private static WorkForDayREST rest;

    public static WorkForDayREST getRest(Context context){
        if (rest == null){
            Retrofit retrofit = new Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(context.getString(R.string.work_for_day_rest_url))
                    .build();
           rest = retrofit.create(WorkForDayREST.class);
        }

        return rest;
    }
}
