package com.example.ltmt_19303_group6.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.ltmt_19303_group6.DbHelper;
import com.example.ltmt_19303_group6.Model.Group_Product;

import java.util.ArrayList;

public class Group_Product_DAO {
    DbHelper dbHelper ;
    SQLiteDatabase database;

    public Group_Product_DAO(Context context ){
        dbHelper = new DbHelper(context);
        database = dbHelper.getWritableDatabase();
    }


    public boolean add_group_product(Group_Product groupProduct){
        ContentValues values = new ContentValues();
        values.put("Name_Group_Product", groupProduct.getName());

        long reslut = database.insert("Group_Product", null, values);

        return  reslut > 0;
    }

    public ArrayList<Group_Product> getList_Group_Product(){
        database = dbHelper.getReadableDatabase();
        ArrayList<Group_Product> list = new ArrayList<>();

        Cursor cursor = database.rawQuery("SELECT ID_Group_Product, Name_Group_Product FROM Group_Product", null);
        if (cursor != null){
            while (cursor.moveToNext()){
                Integer id = cursor.getInt(0);
                String name = cursor.getString(1);

                list.add(new Group_Product(id, name));
            }
            cursor.close();
        }

        return list;
    }
    public Integer getId_Firt(){
        database = dbHelper.getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM Group_Product", null);

        Integer id = null;
        if (cursor != null && cursor.moveToFirst()){
            id = cursor.getInt(0);
            cursor.close();
            return id;
        }else {
            cursor.close();
            return id;
        }
    }
}
