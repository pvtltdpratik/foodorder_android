package com.mca.order_food;

public class mainModel {

    int image;
    String foodname ,foodprice, fooddes;

    public mainModel(int image, String foodname, String foodprice, String fooddes) {
        this.image = image;
        this.foodname = foodname;
        this.foodprice = foodprice;
        this.fooddes = fooddes;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getFoodname() {
        return foodname;
    }

    public void setFoodname(String foodname) {
        this.foodname = foodname;
    }

    public String getFoodprice() {
        return foodprice;
    }

    public void setFoodprice(String foodprice) {
        this.foodprice = foodprice;
    }

    public String getFooddes() {
        return fooddes;
    }

    public void setFooddes(String fooddes) {
        this.fooddes = fooddes;
    }
}