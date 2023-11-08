package com.example.orderfood.models;

import java.util.List;

public class Cart {
    private int id;
    private int userId;
    private List<Integer> listFoodId;
    public Cart(){}

    public Cart(int id, int userId) {
        this.id = id;
        this.userId = userId;
        this.listFoodId=null;
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

    public List<Integer> getFoodId() {
        return listFoodId;
    }

    public void setFoodId(List<Integer> foodId) {
        this.listFoodId = foodId;
    }

    public Cart(int id, int userId, List<Integer> listFoodId) {
        this.id = id;
        this.userId = userId;
        this.listFoodId = listFoodId;
    }
}
