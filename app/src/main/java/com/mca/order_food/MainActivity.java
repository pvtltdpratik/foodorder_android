package com.mca.order_food;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    dbHandler db ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         setTheme(R.style.Theme_FoodTEST1);
        setContentView(R.layout.activity_main);

        db = new dbHandler(this);
        RecyclerView rv = findViewById(R.id.mainRV);
        FloatingActionButton action_btn = findViewById(R.id.action_btn);

        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        getSupportActionBar().setTitle("Food Menu");
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.WHITE));


        ArrayList<mainModel> list = new ArrayList<>();
        list.add(new mainModel(R.drawable.img,"Shawarma","4","Famous chinese Shawarma is available with 20% discount."));
        list.add(new mainModel(R.drawable.img_9,"Soft Drinks","15","Your favourite Soft Drinks are available with 20% discount."));
        list.add(new mainModel(R.drawable.img_1,"Samosa","1","French Samosa with 10% discount."));
        list.add(new mainModel(R.drawable.img_11,"Noodle","3","Chinese Noodle available with 50% discount."));
        list.add(new mainModel(R.drawable.img_3,"Burger","1","Burger is available with 20% discount."));
        list.add(new mainModel(R.drawable.img_4,"Sandwich","4","French Sandwich is available with 10% discount."));
        list.add(new mainModel(R.drawable.img_5,"Milkshake","5","Most favourite Milkshake is available with 20% discount."));
        list.add(new mainModel(R.drawable.img_6,"Muffin","11","Muffin is available with 20% discount."));
        list.add(new mainModel(R.drawable.img_7,"Burrito","9","Most famous Burger is available with 20% discount."));
        list.add(new mainModel(R.drawable.img_8,"Taco","5","Mexico Tacos are available with 20% discount."));
        list.add(new mainModel(R.drawable.img_10,"Onion Ring","1","Onion Ring is available with 20% discount."));
        list.add(new mainModel(R.drawable.img_12,"Pancake","3","Pancake is available with 20% discount."));

        mainAdapter adapter = new mainAdapter(list,this);

        rv.setAdapter(adapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rv.setLayoutManager(layoutManager);


        action_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),order.class));
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.tab_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.logout) {

            SharedPreferences sharedPreferences = getSharedPreferences("login", MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.clear();
            editor.apply();

            Intent intent = new Intent(MainActivity.this, login.class);
            startActivity(intent);

        }

        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishAffinity();
    }
}