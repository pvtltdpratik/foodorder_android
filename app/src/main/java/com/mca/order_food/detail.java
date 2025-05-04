package com.mca.order_food;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.mca.order_food.databinding.ActivityDetailBinding;

public class detail extends AppCompatActivity {

    ActivityDetailBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        EditText username = findViewById(R.id.userName);
        EditText phonenumber = findViewById(R.id.userPhone);
        Button submit = findViewById(R.id.submit_btn);
        dbHandler db = new dbHandler(this);
//        fetch the data of user from shared preferece and add to edit text username and phonenumber
        SharedPreferences sharedPreferences = getSharedPreferences("login",MODE_PRIVATE);
        String email = sharedPreferences.getString("email",null);
        Cursor cursor1 = db.getUserDetails(email);
        if (cursor1 != null && cursor1.moveToFirst()) {
            String name = cursor1.getString(1);
            String phone = cursor1.getString(2);
            username.setText(name);
            phonenumber.setText(phone);
        }
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        getSupportActionBar().setTitle("Order");
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();

        if (intent.getIntExtra("type",0)==1) {
            int img = intent.getIntExtra("image", 0);
            int price = Integer.parseInt(intent.getStringExtra("foodprice"));
            String name = intent.getStringExtra("foodname");
            String des = intent.getStringExtra("fooddes");
            String type = intent.getStringExtra("type");

            binding.detailImage.setImageResource(img);
            binding.detailName.setText(name);
            binding.detailDes.setText(des);
            binding.detailprice23.setText(price + "");

            submit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    String Uname = username.getText().toString();
                    String Uphone = phonenumber.getText().toString();

                    if (TextUtils.isEmpty(Uname)) {
                        Toast.makeText(detail.this, "Name is required", Toast.LENGTH_SHORT).show();
                    } else if (TextUtils.isEmpty(Uphone)) {
                        Toast.makeText(detail.this, "Phone number is required", Toast.LENGTH_SHORT).show();
                    } else {


                        boolean i = db.insert_data(img, name, des, price, Uname, Uphone);
                        if (i == true) {
                            Toast.makeText(detail.this, "Order submitted", Toast.LENGTH_SHORT).show();

                            username.setText("");
                            phonenumber.setText("");

                            startActivity(new Intent(getApplicationContext(), MainActivity.class));

                        } else {
                            Toast.makeText(detail.this, "Failed", Toast.LENGTH_SHORT).show();
                        }
                    }


                }
            });

        }
        else {

            int id = getIntent().getIntExtra("id",0);
            Cursor cursor = db.getorders_byid(id);
            int image = cursor.getInt(1);
            binding.detailImage.setImageResource(image);
            binding.detailName.setText(cursor.getString(2));
            binding.detailDes.setText(cursor.getString(3));
            binding.detailprice23.setText(cursor.getString(4)+"");
            binding.userName.setText(cursor.getString(5));
            binding.userPhone.setText(cursor.getString(6));

            binding.submitBtn.setText("Update Order");


            binding.submitBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    boolean a = db.update_data(
                    image,binding.detailName.getText().toString(),
                            binding.detailDes.getText().toString(),
                            Integer.parseInt(binding.detailprice23.getText().toString()),
                            binding.userName.getText().toString(),
                            binding.userPhone.getText().toString(),id );


                    if (a==true){
                        Toast.makeText(detail.this, "Order Updated", Toast.LENGTH_SHORT).show();

                        binding.userName.setText("");
                        binding.userPhone.setText("");
                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
                    }
                    else {
                        Toast.makeText(detail.this, "Error", Toast.LENGTH_SHORT).show();
                    }
                }
            });

        }

    }


}