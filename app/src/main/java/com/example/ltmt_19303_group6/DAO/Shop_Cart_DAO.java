package com.example.ltmt_19303_group6.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.ltmt_19303_group6.DbHelper;
import com.example.ltmt_19303_group6.Model.Shop_Cart_Model;

import java.util.ArrayList;

public class Shop_Cart_DAO {
    DbHelper dbHelper;
    SQLiteDatabase database;

    public Shop_Cart_DAO(Context context){
        dbHelper = new DbHelper(context);
        database = dbHelper.getWritableDatabase();
    }

    public boolean add_product_to_Shopcar(Shop_Cart_Model shopCartModel){
        ContentValues values = new ContentValues();
        values.put("Quatity", shopCartModel.getQuantity());
        values.put("Unit_Price", shopCartModel.getUnitPrice());
        values.put("ID_Product", shopCartModel.getIdProduct());
        values.put("Subtotal", shopCartModel.getSubtotal());

        long reslut = database.insert("Shopping_Cart", null, values);

        return reslut > 0;
    }

    public ArrayList<Shop_Cart_Model> getList_Product_In_ShopCart(){
        database = dbHelper.getReadableDatabase();
        ArrayList<Shop_Cart_Model> list = new ArrayList<>();

        Cursor cursor = database.rawQuery("SELECT  Product.ID_Product, Product.Name_Product, Product.Price_Product,Shopping_Cart.Quatity, Shopping_Cart.Unit_Price, Shopping_Cart.ID_Product, Shopping_Cart.Subtotal , MIN(Image.Image) FROM Shopping_Cart JOIN Product ON Shopping_Cart.ID_Product = Product.ID_Product JOIN Image ON Product.ID_Product = Image.ID_Product GROUP BY Product.Name_Product, Product.Price_Product", null);

        if (cursor != null){
            while (cursor.moveToNext()){
                Integer id_product = cursor.getInt(0);
                String name = cursor.getString(1);
                Integer price = cursor.getInt(2);
                Integer quantity_shopcart = cursor.getInt(3);
                Integer TongGiaSoLuong = cursor.getInt(4);
                Integer id_product_shopcart = cursor.getInt(5);
                Integer Subtotal = cursor.getInt(6);
                byte[] image = cursor.getBlob(7);
              //  Integer idCart, int quantity, int unitPrice, int idProduct, int subtotal, String name_Product, Integer price_product, byte[] image
                list.add(new Shop_Cart_Model(id_product_shopcart, quantity_shopcart, TongGiaSoLuong, id_product,Subtotal, name,  price, image));
            }
        }

        return list;
    }

    public boolean delete(Integer id){

        long reslut = database.delete("Shopping_Cart", "ID_Product = ?", new String[]{String.valueOf(id)});

        return reslut > 0;
    }
}
