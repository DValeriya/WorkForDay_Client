package com.example.workforday;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.workforday.models.HashTag;
import com.example.workforday.models.WorkApplication;
import com.example.workforday.retrofit.WorkForDayAPI;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

import me.gujun.android.taggroup.TagGroup;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddWorkApplicationFragment extends Fragment {

    private View view;
    private static final String TAG = "AddWorkApplication";

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {

        this.view = inflater.inflate(R.layout.add_work_application_fragment, null);

        SharedPreferences userInfo = getActivity().getSharedPreferences(getString(R.string.user_info), Context.MODE_PRIVATE);

        TextView nameTextView = view.findViewById(R.id.add_worker_application_name);
        nameTextView.setText(userInfo.getString(getString(R.string.user_info_name), "NAME"));

        TagGroup phonesTagGroup = view.findViewById(R.id.add_worker_application_phones);
        try {
            phonesTagGroup.setTags(userInfo.getString(getString(R.string.user_info_phones), null).split(";"));
        } catch (NullPointerException ignored){}

        View addButton = this.view.findViewById(R.id.add_bottom_wapp);
        addButton.setOnClickListener(v -> {
            TagGroup phones = this.view.findViewById(R.id.add_worker_application_phones);
            EditText description =  this.view.findViewById(R.id.add_worker_application_description);
            TagGroup hashtags = this.view.findViewById(R.id.add_worker_application_hashtags);

            String phoneNumbers = TextUtils.join(";", phones.getTags());

            List<HashTag> listOfHashtags = new ArrayList<>();
            for (String hashtag : hashtags.getTags()) {
                listOfHashtags.add(new HashTag(hashtag));
            }

            WorkApplication workApplication = new WorkApplication();
            workApplication.setPhoneNumbers(phoneNumbers);
            workApplication.setDescription(description.getText().toString());
            workApplication.setHashTags(listOfHashtags);
            saveWorkApllication(workApplication);


        });
        return this.view;
    }

    @Override
    public void onResume() {
        super.onResume();
        BottomNavigationView view = getActivity().findViewById(R.id.navigation);
        view.getMenu().getItem(1).setChecked(true);
    }

    private void saveWorkApllication(WorkApplication workApplication){
        WorkForDayAPI.getRest(getContext()).addWorkApplication(workApplication).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Log.d(TAG, "onResponse: responseCode: " + response.code());
                if (response.isSuccessful()) {}
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });
    }
}
