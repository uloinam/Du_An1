package com.example.ltmt_19303_group6.DAO;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.ltmt_19303_group6.DbHelper;
import com.example.ltmt_19303_group6.Model.User_Model;

public class User_DAO {
    private DbHelper dbHelper;
    private SQLiteDatabase database;

    public User_DAO(Context context){
        dbHelper = new DbHelper(context);
        database = dbHelper.getWritableDatabase();
    }

    public User_Model get_OneUser(String user, String password){
       database = dbHelper.getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT ID_Empolyee, UserName_Employee, Password_Employee, Position, ID_Profiles FROM Employee WHERE UserName_Employee = ? AND Password_Employee = ?", new String[]{user, password});
        User_Model userModel = null;
        if (cursor != null){
            while (cursor.moveToNext()){
                Integer Id_Empolyee = cursor.getInt(0);
                String userName_Empolyee = cursor.getString(1);
                String passWord_Empolyee = cursor.getString(2);
                String posision = cursor.getString(3);
                Integer Id_Profile = cursor.getInt(4);
                userModel = new User_Model(Id_Empolyee, Id_Profile, userName_Empolyee, passWord_Empolyee, posision);
            }
        }
        return userModel;
    }
}
