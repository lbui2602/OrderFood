package com.example.orderfood.models.database;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.orderfood.models.Favorite;
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
    public void addUser(User user){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("username", user.getUsername());
        values.put("first_name",user.getFirstname());
        values.put("last_name",user.getLastname());
        values.put("password",user.getPassword());
        values.put("image",user.getImage());
        values.put("phone_number",user.getPhoneNumber());
        values.put("address",user.getAddress());
        db.insert(TABLE_NAME, null, values);
        db.close();
    }
    public void deleteUserByUSerId(int id){
        SQLiteDatabase db=dbHelper.getWritableDatabase();
        db.delete(TABLE_NAME,"id = ?",new String[]{id+""});
        db.close();
    }
    @SuppressLint("Range")
    public User getUserByUsername(String username) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String[] projection = {
                "user_id",
                "username",
                "first_name",
                "last_name",
                "password",
                "image",
                "phone_number",
                "address"
        };

        String selection = "username LIKE ?";
        String[] selectionArgs = { username };

        Cursor cursor = db.query(
                "User",           // Tên bảng
                projection,       // Các cột bạn muốn lấy
                selection,        // Mệnh đề WHERE
                selectionArgs,    // Đối số cho mệnh đề WHERE
                null,
                null,
                null
        );

        User user = null;

        if (cursor != null && cursor.moveToFirst()) {
            // Đảm bảo rằng con trỏ có ít nhất một dòng trước khi cố gắng truy xuất dữ liệu
            user = new User(
                    cursor.getInt(cursor.getColumnIndex("user_id")),
                    cursor.getString(cursor.getColumnIndex("username")),
                    cursor.getString(cursor.getColumnIndex("first_name")),
                    cursor.getString(cursor.getColumnIndex("last_name")),
                    cursor.getString(cursor.getColumnIndex("password")),
                    cursor.getString(cursor.getColumnIndex("image")),
                    cursor.getString(cursor.getColumnIndex("phone_number")),
                    cursor.getString(cursor.getColumnIndex("address"))
            );
        }

        // Đóng con trỏ sau khi sử dụng
        if (cursor != null) {
            cursor.close();
        }

        return user;
    }
}
