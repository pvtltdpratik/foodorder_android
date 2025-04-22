package com.mca.order_food;

public class orderModel {

    int order_image ;
    String order_name , order_number , order_price , orderPhoneNumber;

    public orderModel(int order_image, String order_name, String order_number, String order_price, String orderPhoneNumber) {
        this.order_image = order_image;
        this.order_name = order_name;
        this.order_number = order_number;
        this.order_price = order_price;
        this.orderPhoneNumber = orderPhoneNumber;
    }

    public int getOrder_image() {
        return order_image;
    }

    public void setOrder_image(int order_image) {
        this.order_image = order_image;
    }

    public String getOrder_name() {
        return order_name;
    }

    public void setOrder_name(String order_name) {
        this.order_name = order_name;
    }

    public String getOrder_number() {
        return order_number;
    }

    public void setOrder_number(String order_number) {
        this.order_number = order_number;
    }

    public String getOrder_price() {
        return order_price;
    }

    public void setOrder_price(String order_price) {
        this.order_price = order_price;
    }

    public String getOrderPhoneNumber() {
        return orderPhoneNumber;
    }

    public void setOrderPhoneNumber(String orderPhoneNumber) {
        this.orderPhoneNumber = orderPhoneNumber;
    }
}
