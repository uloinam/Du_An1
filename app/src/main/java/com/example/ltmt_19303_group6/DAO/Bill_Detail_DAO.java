package com.example.ltmt_19303_group6.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.ltmt_19303_group6.DbHelper;
import com.example.ltmt_19303_group6.Model.Bill_Detail_Model;

public class Bill_Detail_DAO {
    DbHelper dbHelper;
    SQLiteDatabase database;

    public  Bill_Detail_DAO (Context context){
        dbHelper = new DbHelper(context);
        database = dbHelper.getWritableDatabase();
    }

    public boolean add_detail_bill(Bill_Detail_Model billDetailModel){
        ContentValues values = new ContentValues();
        values.put("Quantity", billDetailModel.getQuantity());
        values.put("Price_Per_Unit", billDetailModel.getPricePerUnit());

        long reslut = database.insert("Bill_Detail", null, values);

        return  reslut > 0;
    }
    public Integer getOne_LastIdex(){
        database = dbHelper.getReadableDatabase();
        Integer id = null;
        Cursor cursor = database.rawQuery("SELECT ID_Order_Detail FROM Bill_Detail ORDER BY ID_Order_Detail DESC LIMIT 1", null);

        // Check if cursor is not null and has data
        if (cursor != null && cursor.moveToFirst()) {
            id = cursor.getInt(0); // Retrieve the value from the first column
        }

        // Close the cursor to avoid memory leaks
        if (cursor != null) {
            cursor.close();
        }

        return id;
    }
}
