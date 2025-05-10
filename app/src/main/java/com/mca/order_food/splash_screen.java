package com.mca.order_food;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class splash_screen extends AppCompatActivity {

    Button btn;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);
        btn = findViewById(R.id.startbtn);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        btn.setOnClickListener(
                v -> {
                    // if the shared preferences contains the email, go to main activity
                    // else go to login activity
                     SharedPreferences sharedPreferences = getSharedPreferences("login",MODE_PRIVATE);
                     if (sharedPreferences.contains("email")){
                         Intent intent = new Intent(splash_screen.this,MainActivity.class);
                         startActivity(intent);
                         finish();
                     }else{
                         Intent intent = new Intent(splash_screen.this,login.class);
                         startActivity(intent);
                         finish();
                     }
                }
        );

    }
}
