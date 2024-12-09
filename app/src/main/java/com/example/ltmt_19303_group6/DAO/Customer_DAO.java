package com.example.ltmt_19303_group6.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.ArrayAdapter;

import com.example.ltmt_19303_group6.DbHelper;
import com.example.ltmt_19303_group6.Model.Customer_Model;

import java.util.ArrayList;

public class Customer_DAO {
    DbHelper dbHelper;
    SQLiteDatabase database;

    public  Customer_DAO(Context context){
        dbHelper = new DbHelper(context);
        database = dbHelper.getWritableDatabase();
    }

    public boolean add_Customer(Customer_Model customerModel){
        ContentValues values = new ContentValues();
        values.put("Name_Customer", customerModel.getName_Customer());
        values.put("Age_Customer", customerModel.getAge_Customer());
        values.put("Address_Customer", customerModel.getAddress_Customer());
        values.put("PhoneNumber_Customer", customerModel.getPhoneNumber_Customer());

        long reslut = database.insert("Customer", null, values);

        return reslut > 0;
    }

    public ArrayList<Customer_Model> getList_Customer(){
        database = dbHelper.getReadableDatabase();
        ArrayList<Customer_Model> list = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT ID_Customer, Name_Customer,  Age_Customer, Address_Customer,PhoneNumber_Customer FROM Customer ",null );
        if (cursor != null){
            while (cursor.moveToNext()){
                Integer id = cursor.getInt(0);
                String name = cursor.getString(1);
                Integer age = cursor.getInt(2);
                String address = cursor.getString(3);
                String phone_Number = cursor.getString(4);

                list.add(new Customer_Model(id, name, age, address, phone_Number));

            }
        }
        cursor.close();

        return list;
    }

    public boolean update_Customer(Customer_Model customerModel) {
        // Tạo đối tượng ContentValues để chứa các cặp giá trị cột và dữ liệu mới
        ContentValues values = new ContentValues();
        values.put("Name_Customer", customerModel.getName_Customer());
        values.put("Age_Customer", customerModel.getAge_Customer());
        values.put("Address_Customer", customerModel.getAddress_Customer());
        values.put("PhoneNumber_Customer", customerModel.getPhoneNumber_Customer());

        // Xây dựng điều kiện WHERE để xác định dòng cần cập nhật
        String whereClause = "ID_Customer = ?";
        String[] whereArgs = {String.valueOf(customerModel.getId_Customer())};

        // Gọi phương thức update() của SQLiteDatabase
        int result = database.update("Customer", values, whereClause, whereArgs);

        // Kiểm tra xem có dòng nào bị ảnh hưởng không
        return result > 0;
    }

    public Integer getOne_LastIdex(){
        database = dbHelper.getReadableDatabase();
        Integer id = null;
        Cursor cursor = database.rawQuery("SELECT ID_Customer FROM Customer ORDER BY ID_Customer DESC LIMIT 1", null);
        if (cursor != null && cursor.moveToFirst()){
            id = cursor.getInt(0);
        }

        if (cursor != null) {
            cursor.close();
        }
        return id;
    }
}
