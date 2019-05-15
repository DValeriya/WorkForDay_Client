package com.redteam.workforday;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class AddFragment extends Fragment {

    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_add, null);

        View addWorkerApplicationButton = view.findViewById(R.id.add_worker_application_button);
        addWorkerApplicationButton.setOnClickListener(v -> {
            ((MainActivity) getActivity()).loadFragment(new AddWorkApplicationFragment());
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
