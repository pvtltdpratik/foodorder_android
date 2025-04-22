package com.mca.order_food;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class mainAdapter extends RecyclerView.Adapter<mainAdapter.viewholder>{

    ArrayList<mainModel> list;
    Context context;

    public mainAdapter(ArrayList<mainModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.main_rv_layout,parent,false);
        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {

        mainModel model = list.get(position);
        holder.food.setImageResource(model.getImage());
        holder.foodname.setText(model.getFoodname());
        holder.foodprice.setText(model.getFoodprice());
        holder.fooddes.setText(model.getFooddes());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context.getApplicationContext(),detail.class);
                intent.putExtra("image",model.getImage());
                intent.putExtra("foodname",model.getFoodname());
                intent.putExtra("foodprice",model.getFoodprice());
                intent.putExtra("fooddes",model.getFooddes());
                intent.putExtra("type",1);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewholder extends RecyclerView.ViewHolder {

        ImageView food;
        TextView foodname , foodprice , fooddes;

        public viewholder(@NonNull View itemView) {
            super(itemView);

            food = itemView.findViewById(R.id.order_image);
            foodname = itemView.findViewById(R.id.orderName);
            foodprice = itemView.findViewById(R.id.Price);
            fooddes = itemView.findViewById(R.id.food_description);
        }
    }
}
