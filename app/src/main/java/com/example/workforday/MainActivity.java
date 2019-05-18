package com.example.workforday;

import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentContainer;
import androidx.fragment.app.FragmentController;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_fragment, new SearchFragment())
                .commit();

        BottomNavigationView bottomNavigationView = findViewById(R.id.navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(menuItem -> {
            switch (menuItem.getItemId()) {
                case R.id.navigation_sign:
                    Intent i = new Intent(this, SignInActivity.class);
                    startActivity(i);
                    break;
                case R.id.navigation_search:
                    loadFragment(new SearchFragment());
                    break;
                case R.id.navigation_add:
                    loadFragment(new AddFragment());
                    break;
                case R.id.navigation_fav:
                    loadFragment(new SavedFragment());
                    break;
            }
            return true;
        });
    }

    public boolean loadFragment(Fragment fragment) {
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.main_fragment, fragment)
                    .addToBackStack(fragment.toString())
                    .addToBackStack(null)
                    .commit();
            return true;
        }
        return false;
    }

    public void fragmentBackPressed(){
        FragmentManager fm = getFragmentManager();
        if (fm.getBackStackEntryCount() > 0){
            fm.popBackStack();
        } else {
            onBackPressed();
        }
    }
}
