package com.mca.order_food;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class dbHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 123;
    public dbHandler(Context context) {
        super(context, "mydb.db", null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String q = "create table orders (id integer primary key autoincrement , foodImage int , foodname text , fooddes text , foodPrice int , userName text , phoneNumber text)";
        sqLiteDatabase.execSQL(q);

        String n = "create table regis (id integer primary key autoincrement , email text , password text)";
        sqLiteDatabase.execSQL(n);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("drop table if exists orders");

        sqLiteDatabase.execSQL("drop table if exists regis");
        onCreate(sqLiteDatabase);

    }

    public boolean insert_data(int foodimage ,String foodName , String fooddes , int foodPrice , String userName , String phoneNumber){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("foodImage",foodimage);
        values.put("foodname",foodName);
        values.put("fooddes",fooddes);
        values.put("foodPrice",foodPrice);
        values.put("userName",userName);
        values.put("phoneNumber",phoneNumber);
        long r = sqLiteDatabase.insert("orders",null,values);
        if (r==-1){
            return false;
        }
        else {
            return true;
        }
    }


    public ArrayList<orderModel> get_order(){

        ArrayList<orderModel> orders = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        Cursor cursor = sqLiteDatabase.rawQuery("select id , foodImage , foodname , foodPrice , phoneNumber from orders",null);

       if (cursor.moveToFirst()){
           while (cursor.moveToNext()){

               int id1 = cursor.getInt(0);
               int foodimage1 = cursor.getInt(1);
               String foodname1 = cursor.getString(2);
               int foodprice1 = cursor.getInt(3);
               String phone1 = cursor.getString(4);

               orderModel model = new orderModel(foodimage1,foodname1,id1+"",foodprice1+"",phone1);
               orders.add(model);

           }
       }
       return orders;
    }

    public boolean update_data(int foodimage , String foodname , String fooddes , int foodprice , String username , String userphone , int id){

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("foodImage",foodimage);
        values.put("foodname",foodname);
        values.put("fooddes",fooddes);
        values.put("foodPrice",foodprice);
        values.put("userName",username);
        values.put("phoneNumber",userphone);

        long r = sqLiteDatabase.update("orders",values, "id="+id,null);
        if (r==-1){
            return false;
        }
        else{
            return true;
        }
    }

    public Cursor getorders_byid(int id){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from orders where id="+id,null);
        if(cursor != null && cursor.moveToFirst()){

        }
        return cursor;
    }

    public int delete_data(int id){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

       return sqLiteDatabase.delete("orders","id="+id,null);

    }



    public boolean insert_registration_detail(String email , String password){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("email",email);
        values.put("password",password);
        long r = sqLiteDatabase.insert("regis",null,values);
        if (r==-1){
            return false;
        }
        else {
            return true;
        }
    }

    public boolean checkEmail(String email){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from regis where email=?",new String[]{email});
        if (cursor.getCount()>0){
            return true;
        }
        else {
            return false;
        }
    }

    public boolean checkEmailPassword(String email , String password){
        SQLiteDatabase sqLiteDatabase  = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from regis where email=? and password=?",new String[]{email,password});
        if (cursor.getCount()>0){
            return true;
        }
        else {
            return false;
        }
    }

}
