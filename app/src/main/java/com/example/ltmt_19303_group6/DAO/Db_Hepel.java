package com.example.ltmt_19303_group6.DAO;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Db_Hepel extends SQLiteOpenHelper {
    public Db_Hepel(Context context) {
        super(context, "Du_An.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String Crt_Table_Product ="";
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
