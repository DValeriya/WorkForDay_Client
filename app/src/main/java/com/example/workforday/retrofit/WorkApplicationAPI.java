package com.example.workforday.retrofit;

import android.content.Context;

import com.example.workforday.R;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WorkApplicationAPI {
    private static Retrofit retrofit = null;

    public static Retrofit getClient(Context context){
        if (retrofit == null){
            retrofit = new Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(context.getString(R.string.work_for_day_rest_url))
                    .build();
        }

        return retrofit;
    }
}
