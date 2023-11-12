package com.example.orderfood.models;

public class MenuFood {
    private int id;
    private int foodId;
    private int menuId;

    public MenuFood() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFoodId() {
        return foodId;
    }

    public void setFoodId(int foodId) {
        this.foodId = foodId;
    }

    public int getMenuId() {
        return menuId;
    }

    public void setMenuId(int menuId) {
        this.menuId = menuId;
    }

    public MenuFood(int id, int foodId, int menuId) {
        this.id = id;
        this.foodId = foodId;
        this.menuId = menuId;
    }
}
