package com.example.ltmt_19303_group6.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.ltmt_19303_group6.DbHelper;
import com.example.ltmt_19303_group6.Model.Category_Model;

import java.util.ArrayList;

public class Category_DAO {
    private DbHelper dbHelper;
    private SQLiteDatabase database;

    public Category_DAO(Context context){
        dbHelper = new DbHelper(context);
        database = dbHelper.getWritableDatabase();
    }

    public boolean add_Category(Category_Model categoryModel){
        ContentValues values = new ContentValues();
        values.put("Name_Category", categoryModel.getName_category());
        values.put("ID_Group_Product", categoryModel.getId_Group_Product());
        long reslut = database.insert("Category", null, values);

        return reslut > 0;
    }

    public ArrayList<Category_Model> getLit_Category (Integer id_group_product){
        database = dbHelper.getReadableDatabase();
        ArrayList<Category_Model> list = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT ID_Category, Name_Category, ID_Group_Product FROM Category WHERE ID_Group_Product = ?", new String[]{String.valueOf(id_group_product)});

        if (cursor != null){
            while (cursor.moveToNext()){
                Integer id = cursor.getInt(0);
                String name = cursor.getString(1);
                Integer id_Product = cursor.getInt(2);
                list.add(new Category_Model(id, name, id_Product));
            }
            cursor.close();
        }
        return list;
    }


    }

