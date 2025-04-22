package com.mca.order_food;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.mca.order_food.databinding.ActivitySignUpBinding;

public class SignUp extends AppCompatActivity {

    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    ActivitySignUpBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        getSupportActionBar().hide();

        dbHandler db = new dbHandler(this);

        binding.btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String mail = binding.Semail.getText().toString().trim();
                String pass = binding.Spassword.getText().toString().trim();
                String confirmPass = binding.SconfirmPassword.getText().toString().trim();


                if (TextUtils.isEmpty(mail)){
                    Toast.makeText(SignUp.this, "Enter Email", Toast.LENGTH_SHORT).show();
                }
                else if (TextUtils.isEmpty(pass)){
                    Toast.makeText(SignUp.this, "Enter Password", Toast.LENGTH_SHORT).show();
                }
                else if (TextUtils.isEmpty(confirmPass)){
                    Toast.makeText(SignUp.this, "Enter Confirm Password", Toast.LENGTH_SHORT).show();
                }
                else if (!pass.equals(confirmPass)){
                    Toast.makeText(SignUp.this, "Password is not matching", Toast.LENGTH_SHORT).show();
                }
                else if (!mail.matches(emailPattern)){
                    Toast.makeText(SignUp.this, "invalid email", Toast.LENGTH_SHORT).show();
                }
                else{
                        boolean isAlrady  =  db.checkEmail(mail);
                        if (isAlrady==true){
                            Toast.makeText(SignUp.this, "Email is already Registered.", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            boolean isRegistered = db.insert_registration_detail(mail,pass);
                            if (isRegistered == true){
                                startActivity(new Intent(getApplicationContext(),MainActivity.class));
                                Toast.makeText(SignUp.this, "Registered successfully", Toast.LENGTH_SHORT).show();
                            }
                            else {
                                Toast.makeText(SignUp.this, "Error", Toast.LENGTH_SHORT).show();
                            }
                        }
                }
            }
        });


        binding.alradyHaveAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),login.class));
                finish();
            }
        });
    }
}