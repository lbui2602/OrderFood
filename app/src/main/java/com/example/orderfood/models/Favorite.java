package com.example.orderfood.models;

import java.util.List;

public class Favorite {
    private int id;
    private int userId;
    private List<Integer> listFavoriteFoodId;

    public Favorite(int id, int userId, List<Integer> listFavoriteFoodId) {
        this.id = id;
        this.userId = userId;
        this.listFavoriteFoodId = listFavoriteFoodId;
    }

    public Favorite() {
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

    public List<Integer> getListFavoriteFoodId() {
        return listFavoriteFoodId;
    }

    public void setListFavoriteFoodId(List<Integer> listFavoriteFoodId) {
        this.listFavoriteFoodId = listFavoriteFoodId;
    }
}
