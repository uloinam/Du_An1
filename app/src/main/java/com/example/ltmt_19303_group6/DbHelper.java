package com.example.ltmt_19303_group6;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {

    public DbHelper(Context context) {
        super(context, "DuAn1.db", null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        // Tạo bảng Profiles
        String CrTab_Profiles = "CREATE TABLE Profiles (ID_Profiles INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, Name TEXT NOT NULL, Age INTEGER NOT NULL, Email TEXT, Phone_Number TEXT NOT NULL, Avartar BLOB);";
        db.execSQL(CrTab_Profiles);

        // tạo bảng User
        String CrTab_User = "CREATE TABLE Employee (ID_Empolyee INTEGER PRIMARY KEY AUTOINCREMENT, UserName_Employee TEXT NOT NULL, Password_Employee TEXT, Position TEXT, ID_Profiles INTEGER CONSTRAINT fk_profile REFERENCES Profiles (ID_Profiles))";
        db.execSQL(CrTab_User);

        // Tạo 1 profiles admin
        String Inser_oneProfiles = "INSERT INTO Profiles (Name, Age, Email, Phone_Number, Avartar) VALUES ('Nam', 19, 'namphamtrong123@gmail.com', '0964941802', null)";
        db.execSQL(Inser_oneProfiles);

        // Tạo 1 taài khoản  admin
        String Insert_oneUser = "INSERT INTO Employee (UserName_Employee, Password_Employee, Position, ID_Profiles) VALUES ('nampham672005', 'namphamtrong1', 'admin', 1)";
        db.execSQL(Insert_oneUser);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (newVersion > oldVersion){
            db.execSQL("DROP TABLE IF EXISTS Employee");
            db.execSQL("DROP TABLE IF EXISTS Profiles");
            onCreate(db);
        }
    }
}
