package com.example.orderfood.models;

public class Food {
    private int id;
    private String name;
    private String price;
    private String image;
    private int menuId;

    public Food(int id, String name, String price, String image, int menuId) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.image = image;
        this.menuId = menuId;
    }
    public Food(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getMenuId() {
        return menuId;
    }

    public void setMenuId(int menuId) {
        this.menuId = menuId;
    }
}
