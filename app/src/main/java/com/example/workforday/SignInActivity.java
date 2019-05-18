package com.example.workforday;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.workforday.models.User;
import com.example.workforday.retrofit.WorkForDayAPI;

import okhttp3.Credentials;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignInActivity extends AppCompatActivity {

    private final static String TAG = "SingInActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        TextView textView = findViewById(R.id.sign_up);

        EditText emailEditText = findViewById(R.id.sign_in_email);
        EditText passwordEditText = findViewById(R.id.sign_in_set_password);

        ImageButton imageButton = findViewById(R.id.sign_in_close);

        imageButton.setOnClickListener(v -> finish());
        textView.setOnClickListener(v -> {
            Intent i = new Intent(this, SignUpActivity.class);
            startActivity(i);
        });

         View singInButton = findViewById(R.id.sign_in_button);
         singInButton.setOnClickListener(v -> {
             String email = emailEditText.getText().toString();
             String password = passwordEditText.getText().toString();
             WorkForDayAPI.getRest(this).singIn(Credentials.basic(email, password)).enqueue(new Callback<User>() {
                 @Override
                 public void onResponse(Call<User> call, Response<User> response) {
                     if (response.isSuccessful()) {
                         SharedPreferences userInfo = getSharedPreferences(getString(R.string.user_info), MODE_PRIVATE);
                         SharedPreferences.Editor editor = userInfo.edit();
                         editor.putString(getString(R.string.user_info_name), response.body().getName());
                         editor.putString(getString(R.string.user_info_phones), response.body().getPhoneNumbers());
                         editor.putString(getString(R.string.user_info_email), response.body().getEmail());
                         editor.apply();
                         finish();
                     }else{
                         Log.d(TAG, "onResponse: " + response.code());
                         Toast.makeText(getApplicationContext(), "Unauthorized", Toast.LENGTH_LONG).show();
                     }
                 }

                 @Override
                 public void onFailure(Call<User> call, Throwable t) {
                     Log.d(TAG, "onFailure: singing wrong");
                 }
             });
         });


    }
}
