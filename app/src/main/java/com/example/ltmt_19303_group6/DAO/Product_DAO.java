package com.example.ltmt_19303_group6.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.ltmt_19303_group6.DbHelper;
import com.example.ltmt_19303_group6.Model.Product_Model;

import java.util.ArrayList;

public class Product_DAO {
    DbHelper dbHelper;
    SQLiteDatabase database;

    public Product_DAO(Context context){
        dbHelper = new DbHelper(context);
        database = dbHelper.getWritableDatabase();
    }

    public boolean  add_Product(Product_Model productModel){
        ContentValues values = new ContentValues();
        values.put("Name_Product", productModel.getName());
        values.put("Describle_Product", productModel.getDescrible());
        values.put("Quantity_Product", productModel.getQuantity_Product());
        values.put("Price_Product", productModel.getPrice_product());
        values.put("Created_at", productModel.getCreated());
        values.put("Updated_at", productModel.getUpdated());
        values.put("Supplier", productModel.getSupplier());
        values.put("Height_Product", productModel.getHeight());
        values.put("Weight_Product", productModel.getWeight());
        values.put("Status_Product", productModel.getStatus());
        values.put("wight_Product", productModel.getWight());
        values.put("ID_Brand", productModel.getID_Brand());
        values.put("ID_Category", productModel.getID_Category());

        long reslut = database.insert("Product", null , values);

        return reslut > 0;
    }

    public ArrayList<Product_Model> get_List_Product(){
        database = dbHelper.getReadableDatabase();
        ArrayList<Product_Model> list = new ArrayList<>();

        Cursor cursor = database.rawQuery("SELECT ID_product, Name_Product, Describle_Product, Quantity_Product, Price_Product, Created_at, Updated_at, Supplier, Height_Product, Weight_Product, Status_Product, wight_Product, ID_Brand, ID_Category FROM Product", null);

        if (cursor != null){
            while (cursor.moveToNext()){
                Integer id = cursor.getInt(0);
                String name = cursor.getString(1);
                String describle = cursor.getString(2);
                Integer quantity = cursor.getInt(3);
                Integer price = cursor.getInt(4);
                String created = cursor.getString(5);
                String update = cursor.getString(6);
                String Supplier = cursor.getString(7);
                Integer Height_Product = cursor.getInt(8);
                Integer Weight_Product = cursor.getInt(9);
                Integer Status_Product = cursor.getInt(10);
                Integer wight_Product = cursor.getInt(11);
                Integer ID_Brand = cursor.getInt(12);
                Integer ID_Category = cursor.getInt(13);

                list.add(new Product_Model(id, quantity, price, Height_Product, Weight_Product, Status_Product, wight_Product, ID_Brand, ID_Category, name, describle, created, update, Supplier ));
            }
            cursor.close();
        }
        return  list;
    }

    public ArrayList<Product_Model> getlist_product_one_image(){
        database = dbHelper.getReadableDatabase();
        ArrayList<Product_Model> list  = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT Product.ID_product, Product.Name_Product, Product.Describle_Product, Product.Quantity_Product, Product.Price_Product, Product.Created_at, Product.Updated_at, Product.Supplier, Product.Height_Product, Product.Weight_Product, Product.Status_Product, Product.wight_Product, Product.ID_Brand, Product.ID_Category, MIN(Image.Image) AS Image FROM Product INNER JOIN Image ON Product.ID_product = Image.ID_product GROUP BY Product.ID_product, Product.Name_Product, Product.Describle_Product, Product.Quantity_Product, Product.Price_Product, Product.Created_at, Product.Updated_at, Product.Supplier, Product.Height_Product, Product.Weight_Product, Product.Status_Product, Product.wight_Product, Product.ID_Brand, Product.ID_Category", null);
        if (cursor != null){
            while (cursor.moveToNext()){
                Integer id = cursor.getInt(0);
                String name = cursor.getString(1);
                String describle = cursor.getString(2);
                Integer quantity = cursor.getInt(3);
                Integer price = cursor.getInt(4);
                String created = cursor.getString(5);
                String update = cursor.getString(6);
                String Supplier = cursor.getString(7);
                Integer Height_Product = cursor.getInt(8);
                Integer Weight_Product = cursor.getInt(9);
                Integer Status_Product = cursor.getInt(10);
                Integer wight_Product = cursor.getInt(11);
                Integer ID_Brand = cursor.getInt(12);
                Integer ID_Category = cursor.getInt(13);
                byte[] image = cursor.getBlob(14);
                list.add(new Product_Model(id, quantity, price, Height_Product, Weight_Product, Status_Product, wight_Product, ID_Brand, ID_Category, name, describle, created, update, Supplier, image ));
            }
            cursor.close();
        }
        return list;
    }

    public Product_Model getProduct_detail(){
        database = dbHelper.getReadableDatabase();
        Product_Model productModel = null;
        Cursor cursor = database.rawQuery("SELECT  Product.ID_product, Product.Name_Product, Product.Describle_Product, Product.Quantity_Product, Product.Price_Product, Product.Created_at, Product.Updated_at, Product.Supplier, Product.Height_Product, Product.Weight_Product, Product.Status_Product, Product.wight_Product, Product.ID_Brand, Product.ID_Category, MIN(Image.Image) AS Image FROM Product INNER JOIN Image ON Product.ID_product = Image.ID_product", null);
        if (cursor != null){
            while (cursor.moveToNext()){
                Integer id = cursor.getInt(0);
                String name = cursor.getString(1);
                String describle = cursor.getString(2);
                Integer quantity = cursor.getInt(3);
                Integer price = cursor.getInt(4);
                String created = cursor.getString(5);
                String update = cursor.getString(6);
                String Supplier = cursor.getString(7);
                Integer Height_Product = cursor.getInt(8);
                Integer Weight_Product = cursor.getInt(9);
                Integer Status_Product = cursor.getInt(10);
                Integer wight_Product = cursor.getInt(11);
                Integer ID_Brand = cursor.getInt(12);
                Integer ID_Category = cursor.getInt(13);
                byte[] image = cursor.getBlob(14);
                productModel = new Product_Model(id, quantity, price, Height_Product, Weight_Product, Status_Product, wight_Product, ID_Brand, ID_Category, name, describle, created, update, Supplier, image );
            }
            cursor.close();
        }
        return productModel;
    }
}
