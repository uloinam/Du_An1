package com.example.ltmt_19303_group6.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.ltmt_19303_group6.DbHelper;
import com.example.ltmt_19303_group6.Model.User_Model;

import java.util.ArrayList;

public class User_DAO {
    private DbHelper dbHelper;
    private SQLiteDatabase database;

    public User_DAO(Context context){
        dbHelper = new DbHelper(context);
        database = dbHelper.getWritableDatabase();
    }

    public User_Model get_OneUser(String user, String password){
       database = dbHelper.getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT ID_Empolyee, UserName_Employee, Password_Employee, Position, ID_Profiles, Status FROM Employee WHERE UserName_Employee = ? AND Password_Employee = ?", new String[]{user, password});
        User_Model userModel = null;
        if (cursor != null){
            while (cursor.moveToNext()){
                Integer Id_Empolyee = cursor.getInt(0);
                String userName_Empolyee = cursor.getString(1);
                String passWord_Empolyee = cursor.getString(2);
                String posision = cursor.getString(3);
                Integer Id_Profile = cursor.getInt(4);
                Integer status = cursor.getInt(5);

                userModel = new User_Model(Id_Empolyee,Id_Profile, status, userName_Empolyee, passWord_Empolyee,posision );
            }
        }
        return userModel;
    }

    public boolean addEmpolyee(User_Model userModel){
        ContentValues values = new ContentValues();
        values.put("UserName_Employee", userModel.getUser_Name());
        values.put("Password_Employee", userModel.getUser_Password());
        values.put("Status", userModel.getStatus());
        values.put("Position", userModel.getUser_Posion());
        values.put("ID_Profiles", userModel.getId_Profile());

        long result = database.insert("Employee", null, values);

        return  result > 0;
    }


    public User_Model getOneEmpolyee(Integer id_profile){
        User_Model userModel = null;
        Cursor cursor = database.rawQuery("SELECT e.Id_Empolyee, e.UserName_Employee, e.Position, e.Status, p.ID_Profiles, p.Name, p.Age, p.Email, p.Phone_number, p.Avartar FROM Employee e JOIN Profiles p ON e.ID_Profiles = p.ID_Profiles AND e.ID_Profiles = ?", new String[]{String.valueOf(id_profile)} );

        if (cursor != null){
            while (cursor.moveToNext()){
                Integer id = cursor.getInt(0);
                String user_name = cursor.getString(1);
                String Position = cursor.getString(2);
                Integer Status = cursor.getInt(3);
                Integer ID_Profiles = cursor.getInt(4);
                String name = cursor.getString(5);
                Integer Age = cursor.getInt(6);
                String email = cursor.getString(7);
                String phone_Number = cursor.getString(8);
                byte[] avatar = cursor.getBlob(8);

                userModel = new User_Model(id, ID_Profiles, Status, user_name,Position, name, email, phone_Number, Age, avatar );
            }
            cursor.close();
        }
        return userModel;
    }
}
