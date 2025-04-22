package com.mca.order_food;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class orderAdapter extends RecyclerView.Adapter<orderAdapter.viewHolder>{

    ArrayList<orderModel> orderlist ;
    Context context;

    public orderAdapter(ArrayList<orderModel> orderlist, Context context) {
        this.orderlist = orderlist;
        this.context = context;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.order_rv_layout,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {

        orderModel model = orderlist.get(position);
        holder.orderImage.setImageResource(model.getOrder_image());
        holder.ordername.setText(model.getOrder_name());
        holder.ordernumber.setText(model.getOrder_number());
        holder.orderprice.setText(model.getOrder_price());
        holder.orderPhoneNumber.setText(model.getOrderPhoneNumber());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,detail.class);
                intent.putExtra("id",Integer.parseInt(model.order_number));
                intent.putExtra("type",2);
                context.startActivity(intent);

            }
        });

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                dbHandler db = new dbHandler(context);
                AlertDialog.Builder builder = new AlertDialog.Builder(context);

                builder.setTitle("Deleting Order");
                builder.setMessage("Are you sure want to delete order?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                       if(db.delete_data(Integer.parseInt(model.getOrder_number()))>0);
                            Toast.makeText(context, "Order deleted Successfully", Toast.LENGTH_SHORT).show();

                            context.startActivity(new Intent(context,MainActivity.class));
                    }

                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                }).show();


                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return orderlist.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {

        ImageView orderImage ;
        TextView ordername , ordernumber, orderprice, orderPhoneNumber;

        public viewHolder(@NonNull View itemView) {
            super(itemView);

            orderImage = itemView.findViewById(R.id.order_image);
            ordername = itemView.findViewById(R.id.orderName);
            ordernumber = itemView.findViewById(R.id.orderNumber);
            orderprice = itemView.findViewById(R.id.orderPrice);
            orderPhoneNumber = itemView.findViewById(R.id.orderPhoneNum);
        }
    }
}
