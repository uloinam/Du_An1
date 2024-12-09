package com.example.ltmt_19303_group6.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.ltmt_19303_group6.DbHelper;
import com.example.ltmt_19303_group6.Model.Order_Model;

public class Order_DAO {
    DbHelper dbHelper;
    SQLiteDatabase database;

    public Order_DAO(Context context){
        dbHelper = new DbHelper(context);
        database = dbHelper.getWritableDatabase();
    }

    public boolean add_Order(Order_Model orderModel){
        ContentValues values = new ContentValues();
        values.put("Date_Order", orderModel.getDateOrder());
        values.put("Total_Price",orderModel.getTotalPrice());
        values.put("ID_Customer", orderModel.getIdCustomer());
        values.put("ID_Employee", orderModel.getIdEmployee());
        values.put("ID_Bill_Detail", orderModel.getIdOrderDetail());

        long reslut = database.insert("Orders", null, values);

        return reslut > 0;
    }

    public Integer getOne_LastIdex(){
        database = dbHelper.getReadableDatabase();
        Integer id = null;
        Cursor cursor = database.rawQuery("SELECT ID_Order FROM Orders ORDER BY ID_Order DESC LIMIT 1", null);
        if (cursor != null && cursor.moveToFirst()){
            id = cursor.getInt(0);
        }

        if (cursor != null) {
            cursor.close();
        }
        return id;
    }
}
