package com.example.ltmt_19303_group6.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.ltmt_19303_group6.DbHelper;
import com.example.ltmt_19303_group6.Model.Image_product_Model;

import java.util.ArrayList;

public class Image_DAO {
    DbHelper dbHelper;
    SQLiteDatabase database;

    public Image_DAO(Context context){
        dbHelper = new DbHelper(context);
        database = dbHelper.getWritableDatabase();
    }

    public boolean addImage_Product(Image_product_Model imageProductModel){
        ContentValues values = new ContentValues();
        values.put("Image", imageProductModel.getImage());
        values.put("ID_product", imageProductModel.getId_Product());

        long reslut = database.insert("Image", null, values);

        return reslut > 0;
    }

    public ArrayList<Image_product_Model> get_list_Image_fromProduct(Integer id_Product){
        database = dbHelper.getReadableDatabase();
        ArrayList<Image_product_Model> list = new ArrayList<>();


        Cursor cursor = database.rawQuery("SELECT ID_Image, Image, ID_product FROM Image WHERE ID_product = ?", new String[]{String.valueOf(id_Product)});
        if (cursor != null){
            cursor.moveToFirst();
            while (cursor.moveToNext()){
                Integer id = cursor.getInt(0);
                byte[] image = cursor.getBlob(1);
                Integer id_product_from_image = cursor.getInt(2);

                list.add(new Image_product_Model(id, id_product_from_image, image));
            }
            cursor.close();
        }

        return list;
    }

    public ArrayList<byte[]> getlist_image_product(){
        database = dbHelper.getReadableDatabase();
        ArrayList<byte[]> list = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT Image.Image FROM Product INNER JOIN Image ON Product.ID_product = Image.ID_product", null);
        cursor.moveToFirst();
        if (cursor.moveToNext()){
            byte[] image  = cursor.getBlob(0);
            list.add(image);
        }
        return list;
    }
}
