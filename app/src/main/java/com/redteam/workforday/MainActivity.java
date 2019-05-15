package com.redteam.workforday;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadFragment(new SearchFragment());

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
                    .commit();
            return true;
        }
        return false;
    }
}
