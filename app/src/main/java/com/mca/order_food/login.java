package com.mca.order_food;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.mca.order_food.databinding.ActivityLoginBinding;

public class login extends AppCompatActivity {
    String email ;
    ActivityLoginBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        getSupportActionBar().hide();

        dbHandler db = new dbHandler(this);

        SharedPreferences sharedPreferences = getSharedPreferences("login",MODE_PRIVATE);
        if (sharedPreferences.contains("email")){
            Intent intent = new Intent(login.this,MainActivity.class);
            startActivity(intent);
        }
        else {

        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                email = binding.Lemail.getText().toString().trim();

                String password = binding.Lpassword.getText().toString().trim();

                if (TextUtils.isEmpty(email)){
                    Toast.makeText(login.this, "Enter email", Toast.LENGTH_SHORT).show();
                }
                else if (TextUtils.isEmpty(password)){
                    Toast.makeText(login.this, "Enter password", Toast.LENGTH_SHORT).show();
                }
                else {
                    boolean isValid = db.checkEmailPassword(email,password);
                    if (isValid==true){
                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        finish();

                        SharedPreferences sharedPreferences = getSharedPreferences("login",MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("email","");
                        editor.apply();
                    }
                    else {
                        Toast.makeText(login.this, "Email or Password wrong", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
        }

        binding.createNewAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),SignUp.class));
                finish();
            }
        });
    }
}