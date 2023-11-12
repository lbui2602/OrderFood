package com.example.orderfood.models.database;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.orderfood.models.User;

import java.util.ArrayList;
import java.util.List;

public class UserRepository {

    private final String TABLE_NAME = "User";
    private DBHelper dbHelper;

    public UserRepository(DBHelper dbHelper) {
        this.dbHelper = dbHelper;
    }

    public List<User> getAllUser() {
        String statement = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = dbHelper.getReadableDatabase();


        Cursor cursor = db.rawQuery(statement, null);

        List<User> list = new ArrayList<>();

        while (cursor.moveToNext()) {
            list.add(new User(cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4),
                    cursor.getString(5),
                    cursor.getString(6),
                    cursor.getString(7)));
        }
        return list;
    }

    public User findById(int id) {
        String statement = "SELECT * FROM " + TABLE_NAME + " WHERE id = ?";
        SQLiteDatabase db = dbHelper.getReadableDatabase();


        Cursor cursor = db.rawQuery(statement, new String[]{id + ""});

        return new User(cursor.getInt(0),
                cursor.getString(1),
                cursor.getString(2),
                cursor.getString(3),
                cursor.getString(4),
                cursor.getString(5),
                cursor.getString(6),
                cursor.getString(7));
    }

}
