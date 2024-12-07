package com.example.ltmt_19303_group6.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.ltmt_19303_group6.DbHelper;
import com.example.ltmt_19303_group6.Model.Profile_Model;

import java.util.ArrayList;

public class Profile_DAO {
    DbHelper dbHelper;
    SQLiteDatabase sqLiteDatabase;

    public Profile_DAO(Context context){
        dbHelper = new DbHelper(context);
        sqLiteDatabase = dbHelper.getWritableDatabase();
    }

    public boolean addProfile(Profile_Model profileModel){
        Profile_Model profileModel1 = null;
        ContentValues values = new ContentValues();
        values.put("Name", profileModel.getName_Empolyee());
        values.put("Age", profileModel.getAge());
        values.put("Email", profileModel.getEmail_Empolyee());
        values.put("Phone_Number", profileModel.getPhone_Empolyee());
        values.put("Avartar", profileModel.getAvatar());

        long result = sqLiteDatabase.insert("Profiles", null, values);

        return result > 0;
    }

    public ArrayList<Profile_Model> getProfile(){
        sqLiteDatabase = dbHelper.getReadableDatabase();
        ArrayList<Profile_Model> list = new ArrayList<>();
        Cursor  cursor = sqLiteDatabase.rawQuery("SELECT ID_Profiles, Name,  Age, Email, Phone_Number, Avartar FROM Profiles", null);

        if (cursor != null){
            while (cursor.moveToNext()){
                Integer id = cursor.getInt(0);
                String name = cursor.getString(1);
                Integer Age = cursor.getInt(2);
                String email = cursor.getString(3);
                String phone_number = cursor.getString(4);
                byte[] avatar = cursor.getBlob(5);
                list.add(new Profile_Model(id, Age, name, email, phone_number, avatar));
            }
            cursor.close();
        }
        return list;
    }

    public Profile_Model getProfile(Integer id){
        sqLiteDatabase = dbHelper.getReadableDatabase();
        Profile_Model profileModel = null;
        Cursor  cursor = sqLiteDatabase.rawQuery("SELECT ID_Profiles, Name,  Age, Email, Phone_Number, Avartar FROM Profiles WHERE ID_Profiles = ?", new String[]{String.valueOf(id)});

        if (cursor != null){
            while (cursor.moveToNext()){
                Integer id_Profile = cursor.getInt(0);
                String name = cursor.getString(1);
                Integer Age = cursor.getInt(2);
                String email = cursor.getString(3);
                String phone_number = cursor.getString(4);
                byte[] avatar = cursor.getBlob(5);
                profileModel =  new Profile_Model(id_Profile, Age, name, email, phone_number, avatar);
            }
            cursor.close();
        }
        return profileModel;
    }
}
