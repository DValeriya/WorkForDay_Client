package com.example.workforday;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.Objects;

public class AddFragment extends Fragment {

    private static final String TAG = "AddFragment";
    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_add, null);

        View addWorkerApplicationButton = view.findViewById(R.id.add_worker_application_button);
        addWorkerApplicationButton.setOnClickListener(v -> {
            SharedPreferences userInfo = getActivity().getSharedPreferences(getString(R.string.user_info), Context.MODE_PRIVATE);
            if (userInfo.getString(getString(R.string.user_info_email), null) == null) {
                Log.d(TAG, "onCreateView: Unauthorized");
                Toast.makeText(getContext(), "Unauthorized", Toast.LENGTH_LONG).show();
            }
            else if (getActivity() instanceof  MainActivity){
                ((MainActivity) getActivity()).loadFragment(new AddWorkApplicationFragment());
            }
        });

        View addWorkButton = view.findViewById(R.id.add_work_button);
        addWorkButton.setOnClickListener(v ->{
            ((MainActivity) Objects.requireNonNull(getActivity())).loadFragment(new AddWorkFragment());
        });
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        BottomNavigationView view = getActivity().findViewById(R.id.navigation);
        view.getMenu().getItem(1).setChecked(true);
    }
}
