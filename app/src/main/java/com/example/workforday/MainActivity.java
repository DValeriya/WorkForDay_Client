package com.example.workforday;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

      /*  Button button = findViewById(R.id.sign_in);
        button.setOnClickListener(v -> {
            Intent i = new Intent(this, SignInActivity.class);
        });
*/

    }
}
