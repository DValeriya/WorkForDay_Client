package com.redteam.workforday;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.redteam.workforday.adapters.WorkApplicationListAdapter;
import com.redteam.workforday.models.WorkApplication;
import com.redteam.workforday.retrofit.WorkForDayAPI;
import com.redteam.workforday.retrofit.WorkForDayREST;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchFragment extends Fragment implements OnMapReadyCallback {
    private GoogleMap mMap;
    private MapView mMapView;
    private View mView;

    public SearchFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_search, container, false);

        RecyclerView rv = mView.findViewById(R.id.work_list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(),
                RecyclerView.VERTICAL, false);
        rv.setLayoutManager(layoutManager);

        WorkForDayREST rest = WorkForDayAPI.getRest(getContext());

        rest.getWorkApplications(0, getResources().getInteger(R.integer.conut_of_results_for_page))
                .enqueue(new Callback<List<WorkApplication>>() {
            @Override
            public void onResponse(Call<List<WorkApplication>> call, Response<List<WorkApplication>> response) {
                rv.setAdapter(new WorkApplicationListAdapter(response.body(), getContext()));
                Log.d("SEARCH_FRAGMENT", "onResponse: NAM PIZDA");
            }

            @Override
            public void onFailure(Call<List<WorkApplication>> call, Throwable t) {
                Log.e("SEARCH_FRAGMENT", "onFailure: ",  t);
            }
        });



        return mView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mMapView = mView.findViewById(R.id.map);
        if(mMapView != null){
            mMapView.onCreate(null);
            mMapView.onResume();
            mMapView.getMapAsync(this);

        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

                                                                                                                                                                MapsInitializer.initialize(getContext());
        mMap = googleMap;
        LatLng position = new LatLng(46.469391,30.740883);
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        mMap.addMarker(new MarkerOptions()
                .position(position)
                .title("Odessa")
                .snippet("hello nigga"));

        CameraPosition mCP = CameraPosition.builder()
                .target(position)
                .zoom(16)
                .bearing(0)
                .tilt(45)
                .build();

        mMap.moveCamera(CameraUpdateFactory.newCameraPosition(mCP));
    }

    @Override
    public void onResume() {
        super.onResume();
        BottomNavigationView view = getActivity().findViewById(R.id.navigation);
        view.getMenu().getItem(0).setChecked(true);
    }
}