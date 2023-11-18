package com.example.orderfood.models;

import java.util.List;

public class Favorite {
    private int id;
    private int userId;

    private int foodId;
    public Favorite(){}

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

    public Favorite(int id, int userId, int foodId) {
        this.id = id;
        this.userId = userId;
        this.foodId = foodId;
    }
    public Favorite( int userId, int foodId) {
        this.userId = userId;
        this.foodId = foodId;
    }
}
