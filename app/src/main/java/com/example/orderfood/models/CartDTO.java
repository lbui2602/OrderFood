package com.example.orderfood.models;

public class CartDTO {

    private Food food;
    private int quantity;

    public CartDTO(Food food, int quantity) {
        this.food = food;
        this.quantity = quantity;
    }


    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
