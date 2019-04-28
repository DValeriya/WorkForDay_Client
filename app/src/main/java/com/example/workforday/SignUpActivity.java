package com.example.workforday;

import android.os.Bundle;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class SignUpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        ImageButton imageButton = findViewById(R.id.id_close_sign_up);

        imageButton.setOnClickListener(v->{
                finish();
        });

    }
}
