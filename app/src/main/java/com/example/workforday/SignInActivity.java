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

        TextView textView = findViewById(R.id.id_sing_up_text_view);

        EditText email = findViewById(R.id.id_email_edit_text);
        EditText password = findViewById(R.id.id_password_edit_text);

        ImageButton imageButton = findViewById(R.id.id_close_sign_in);

        imageButton.setOnClickListener(v -> finish());

         textView.setOnClickListener(v -> {
            Intent i = new Intent(this, SignUpActivity.class);
            startActivity(i);
        });
    }
}
