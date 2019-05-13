package com.example.workforday;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.workforday.models.User;
import com.example.workforday.models.WorkApplication;
import com.example.workforday.retrofit.WorkForDayAPI;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddFragment extends Fragment {
    private static final String TAG = "AddFragment";
    private View mView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mView = inflater.inflate(R.layout.fragment_add, null);


        return mView;
    }

    @Override
    public void onResume() {
        super.onResume();
        BottomNavigationView view = getActivity().findViewById(R.id.navigation);
        view.getMenu().getItem(1).setChecked(true);


        WorkApplication workApplication = new WorkApplication();
        workApplication.setDescription("asdfasdfasdfsf");
        workApplication.setHashTags(null);
        workApplication.setPhoneNumbers("+380975540711;+380975124927;");
        SharedPreferences sharedPref = getActivity().getSharedPreferences("User", 0);
        Log.d(TAG, "onCreateView: " + sharedPref.getInt("UserID", 0));
        workApplication.setUser(new User(sharedPref.getInt("UserID", 0)));
        saveWorkApplication(workApplication);
    }

    private void saveWorkApplication(WorkApplication workApplication){
        WorkForDayAPI.getRest(getContext()).addWorkApplication(workApplication).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Log.d(TAG, "onResponse: " + response.code());
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t);

            }
        });
    }
}
