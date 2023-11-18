package com.example.orderfood.models.database;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.orderfood.models.Food;

import java.util.ArrayList;
import java.util.List;

public class FoodRepository {

    private final String TABLE_NAME = "Food";
    private DBHelper dbHelper;

    public FoodRepository(DBHelper dbHelper) {
        this.dbHelper = dbHelper;
    }

    @SuppressLint("Range")
    public Food getById(int id) {
        String statement = "SELECT * FROM " + TABLE_NAME + " WHERE food_id = ?";
        SQLiteDatabase db = dbHelper.getReadableDatabase();


        Cursor cursor = db.rawQuery(statement, new String[]{id + ""});

        return new Food(
                cursor.getInt(cursor.getColumnIndex("food_id")),
                cursor.getString(cursor.getColumnIndex("name")),
                cursor.getString(cursor.getColumnIndex("price")),
                cursor.getString(cursor.getColumnIndex("image")),
                cursor.getInt(cursor.getColumnIndex("menu_id"))
        );
    }
    @SuppressLint("Range")
    public Food getFoodByFoodId(int foodId) {
        Food food = null;
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        // Tạo câu truy vấn SQL
        String query = "SELECT * FROM "+ TABLE_NAME+" WHERE food_id = ?";

        // Thực hiện truy vấn
        Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(foodId)});

        try {
            // Kiểm tra xem Cursor có dữ liệu không
            if (cursor != null && cursor.moveToFirst()) {
                // Lấy thông tin từ Cursor và tạo đối tượng Food
                int id = cursor.getInt(cursor.getColumnIndex("food_id"));
                String name = cursor.getString(cursor.getColumnIndex("name"));
                String price = cursor.getString(cursor.getColumnIndex("price"));
                String image=cursor.getString(cursor.getColumnIndex("image"));
                int menuId= cursor.getInt(cursor.getColumnIndex("menu_id"));
                food = new Food(id,name,price, image,menuId );
            }
        } finally {
            // Đảm bảo đóng Cursor để tránh rò rỉ bộ nhớ
            if (cursor != null) {
                cursor.close();
            }
        }

        return food;
    }
    public List<Food> getFoodByMenuId(int menuId) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        List<Food> foodList = new ArrayList<>();

        String[] projection = {
                "food_id",
                "name",
                "price",
                "image",
                "menu_id"
        };

        String selection = "menu_id = ?";
        String[] selectionArgs = {String.valueOf(menuId)};

        try (Cursor cursor = db.query(
                "Food",
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null
        )) {
            while (cursor != null && cursor.moveToNext()) {
                @SuppressLint("Range") Food food = new Food(
                        cursor.getInt(cursor.getColumnIndex("food_id")),
                        cursor.getString(cursor.getColumnIndex("name")),
                        cursor.getString(cursor.getColumnIndex("price")),
                        cursor.getString(cursor.getColumnIndex("image")),
                        cursor.getInt(cursor.getColumnIndex("menu_id"))
                );
                foodList.add(food);
            }
        } catch (Exception e) {
            // Handle exceptions, log, or throw as needed
            e.printStackTrace();
        }

        return foodList;
    }

}
