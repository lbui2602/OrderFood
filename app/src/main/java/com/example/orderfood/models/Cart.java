package com.example.orderfood.models;

import java.util.List;

public class Cart {
    private static int id=0;
    private int userId;
    private int foodId;
    private int quantity;

    public int getQuantity() {

        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Cart(int userId, int foodId, int quantity) {
        this.id++;
        this.userId = userId;
        this.foodId = foodId;
        this.quantity = quantity;
    }

    public Cart() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getFoodId() {
        return foodId;
    }

    public void setFoodId(int foodId) {
        this.foodId = foodId;
    }
}
