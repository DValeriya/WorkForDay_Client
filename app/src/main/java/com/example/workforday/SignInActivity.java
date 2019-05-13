package com.example.workforday;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SignInActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        TextView textView = findViewById(R.id.sing_up);

        EditText email = findViewById(R.id.sing_up_email);
        EditText password = findViewById(R.id.sign_in_set_password);

        ImageButton imageButton = findViewById(R.id.sing_in_close);

        imageButton.setOnClickListener(v -> finish());

         textView.setOnClickListener(v -> {
            Intent i = new Intent(this, SignUpActivity.class);
            startActivity(i);
        });
    }
}
