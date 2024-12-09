package com.example.ltmt_19303_group6;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import androidx.annotation.Nullable;

import java.io.ByteArrayOutputStream;

public class DbHelper extends SQLiteOpenHelper {

    public DbHelper(Context context) {
        super(context, "DuAn1.db", null, 29);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        // Tạo bảng Profiles
        String CrTab_Profiles = "CREATE TABLE Profiles (ID_Profiles INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, Name TEXT NOT NULL, Age INTEGER NOT NULL, Email TEXT, Phone_Number TEXT NOT NULL, Avartar BLOB);";
        db.execSQL(CrTab_Profiles);

        // tạo bảng User
        String CrTab_User = "CREATE TABLE Employee (ID_Empolyee INTEGER PRIMARY KEY AUTOINCREMENT, UserName_Employee TEXT NOT NULL, Password_Employee TEXT,Status INTEGER, Position TEXT, ID_Profiles INTEGER CONSTRAINT fk_profile REFERENCES Profiles (ID_Profiles))";
        db.execSQL(CrTab_User);


        // Tảo bảng Group_Product
        String Crtabl_Group_Product = " CREATE TABLE Group_Product (ID_Group_Product INTEGER PRIMARY KEY AUTOINCREMENT, Name_Group_Product TEXT)";
        db.execSQL(Crtabl_Group_Product);

        // tạo bảng Category
        String CrTab_Category = "CREATE TABLE Category (ID_Category INTEGER PRIMARY KEY, Name_Category TEXT NOT NULL, ID_Group_Product INTEGER REFERENCES Group_Product (ID_Group_Product))";
        db.execSQL(CrTab_Category);

        // Tạo bảng Brand
        String CrTabl_Brand = "CREATE TABLE Brand (ID_Brand INTEGER PRIMARY KEY AUTOINCREMENT, Name_Brand TEXT, Image_Brand BLOB)";
        db.execSQL(CrTabl_Brand);

        // Tạo 1 profiles admin
        String Inser_oneProfiles = "INSERT INTO Profiles (Name, Age, Email, Phone_Number, Avartar) VALUES ('Nam', 19, 'namphamtrong123@gmail.com', '0964941802', null)";
        db.execSQL(Inser_oneProfiles);

        // Tạo 1 taài khoản  admin
        String Insert_oneUser = "INSERT INTO Employee (UserName_Employee, Password_Employee, Status, Position, ID_Profiles) VALUES ('nampham672005', 'namphamtrong1',1 , 'admin', 0)";
        db.execSQL(Insert_oneUser);

        // tạo bảng product
        String Crtabl_Product = "CREATE TABLE Product (ID_product INTEGER PRIMARY KEY AUTOINCREMENT, Name_Product TEXT, Describle_Product TEXT, Quantity_Product INTEGER, Price_Product INTEGER, Created_at TEXT, Updated_at TEXT, Supplier TEXT, Height_Product INTEGER, Weight_Product INTEGER, Status_Product INTEGER, wight_Product INTEGER, ID_Brand INTEGER REFERENCES Brand (ID_Brand), ID_Category INTEGER REFERENCES Category (ID_Category))";
        db.execSQL(Crtabl_Product);

        String Cratab_Image = "CREATE TABLE Image (ID_Image INTEGER PRIMARY KEY AUTOINCREMENT, Image BLOB, ID_product INTEGER REFERENCES Product (ID_product))";
        db.execSQL(Cratab_Image);

        String Create_Customer = "CREATE TABLE Customer (ID_Customer INTEGER PRIMARY KEY AUTOINCREMENT, Name_Customer TEXT NOT NULL, Age_Customer INTEGER NOT NULL, Address_Customer TEXT NOT NULL, PhoneNumber_Customer TEXT NOT NULL)";
        db.execSQL(Create_Customer);

        String Create_Shop_Cart = "CREATE TABLE Shopping_Cart (ID_Cart INTEGER PRIMARY KEY AUTOINCREMENT, Quatity INTEGER, Unit_Price INTEGER, ID_Product INTEGER REFERENCES Product (ID_product), Subtotal INTEGER, ID_Order REFERENCES Orders (ID_Order))";
        db.execSQL(Create_Shop_Cart);

        String Creatabl_Bill_Detail = "CREATE TABLE Bill_Detail (ID_Order_Detail INTEGER PRIMARY KEY AUTOINCREMENT, Quantity INTEGER, Price_Per_Unit INTEGER)";
        db.execSQL(Creatabl_Bill_Detail);

        String Creatab_Oder = "CREATE TABLE Orders (ID_Order INTEGER PRIMARY KEY AUTOINCREMENT, Date_Order TEXT, Total_Price INTEGER, ID_Customer INTEGER REFERENCES Customer(ID_Customer), ID_Employee INTEGER REFERENCES Employee(ID_Employee), ID_Bill_Detail INTEGER REFERENCES Bill_Detail(ID_Bill_Detail))";
        db.execSQL(Creatab_Oder);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (newVersion > oldVersion){
            db.execSQL("DROP TABLE IF EXISTS Employee");
            db.execSQL("DROP TABLE IF EXISTS Profiles");
            db.execSQL("DROP TABLE IF EXISTS Category");
            db.execSQL("DROP TABLE IF EXISTS Brand");
            db.execSQL("DROP TABLE IF EXISTS Group_Product");
            db.execSQL("DROP TABLE IF EXISTS Product");
            db.execSQL("DROP TABLE IF EXISTS Image");
            db.execSQL("DROP TABLE IF EXISTS Customer");
            db.execSQL("DROP TABLE IF EXISTS Shopping_Cart");
            db.execSQL("DROP TABLE IF EXISTS Bill_Detail");
            db.execSQL("DROP TABLE IF EXISTS Orders");
            onCreate(db);
        }
    }
}
