package com.example.ltmt_19303_group6.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.ltmt_19303_group6.DbHelper;
import com.example.ltmt_19303_group6.Model.Brand_Model;

import java.util.ArrayList;

public class Brand_DAO {
    DbHelper dbHelper;
    SQLiteDatabase database;

    public Brand_DAO(Context context){
        dbHelper = new DbHelper(context);
        database = dbHelper.getWritableDatabase();
    }

    public boolean add_brand(Brand_Model brandModel){
        ContentValues values = new ContentValues();
        values.put("Name_Brand", brandModel.getName());
        values.put("Image_Brand", brandModel.getImage());

        long reslut = database.insert("Brand", null, values);

        return reslut > 0;
    }

    public ArrayList<Brand_Model> get_list_Brand(){
        database = dbHelper.getReadableDatabase();
        ArrayList<Brand_Model> list = new ArrayList<>();

        Cursor cursor = database.rawQuery("SELECT ID_Brand, Name_Brand, Image_Brand FROM Brand", null);
        cursor.moveToFirst();
        if (cursor != null){
            while (cursor.moveToNext()){
                Integer id = cursor.getInt(0);
                String name = cursor.getString(1);
                byte[] image = cursor.getBlob(2);

                list.add(new Brand_Model(id, name, image));
            }
            cursor.close();
        }
        return list;
    }
}
