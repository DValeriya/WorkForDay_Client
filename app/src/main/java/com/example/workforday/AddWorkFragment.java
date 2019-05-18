package com.example.workforday;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.workforday.models.Work;
import com.example.workforday.retrofit.WorkForDayAPI;

import me.gujun.android.taggroup.TagGroup;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddWorkFragment extends Fragment {

    private View view;
    private static final String TAG = "AddWorkFragment";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.add_work_fragment, null);

        View addWorkButton = view.findViewById(R.id.add_work_add_button);
        addWorkButton.setOnClickListener(v -> {
            EditText title = view.findViewById(R.id.add_work_title);
            TagGroup phonesTag = view.findViewById(R.id.add_work_phones);
            EditText budget = view.findViewById(R.id.add_work_budget);
            EditText description = view.findViewById(R.id.add_work_description);
            TagGroup hashtagsTag = view.findViewById(R.id.add_work_hashtags);

            Work work = new Work();
            work.setDescription(description.getText().toString());
            work.setPhoneNumbers(TextUtils.join(";", phonesTag.getTags()));
            work.setTitle(title.getText().toString());

        //    saveWork(new Work());
        });
        return view;
    }


    private void saveWork(Work work){
        WorkForDayAPI.getRest(getContext()).addWork(work).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Log.d(TAG, "onResponse: responseCode: " + response.code());
                if (response.isSuccessful()) {
                    if (getActivity() instanceof MainActivity)
                        ((MainActivity) getActivity()).fragmentBackPressed();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });
    }
}
