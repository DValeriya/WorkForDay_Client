package com.example.workforday;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Credentials;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.workforday.models.User;
import com.example.workforday.retrofit.WorkForDayAPI;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        ImageButton imageButton = findViewById(R.id.sing_up_close);

        imageButton.setOnClickListener(v->{
                finish();
        });

        Button singUpButton = findViewById(R.id.sing_up_button);
        singUpButton.setOnClickListener((view)->{
            EditText fullName = findViewById(R.id.sing_up_full_name);
            EditText email = findViewById(R.id.sing_up_email);
            EditText password = findViewById(R.id.sing_up_set_password);
            User user = new User();
            user.setName(fullName.getText().toString());
            user.setEmail(email.getText().toString());
            user.setPassword(password.getText().toString());
            saveUser(user);
        });

    }

    private void saveUser(User user){
        WorkForDayAPI.getRest(getApplicationContext()).saveNewUser(user).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                SharedPreferences.Editor editor = getSharedPreferences("User", 0).edit();
                editor.putInt("UserID", response.body().getId());
                editor.commit();
                editor.apply();
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });
    }
}
