package com.example.ltmt_19303_group6;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {

    public DbHelper(Context context) {
        super(context, "DuAn1.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CrTab_User = "CREATE TABLE ThuThu (\n" +
                "    MaThuThu TEXT PRIMARY KEY\n" +
                "                  NOT NULL,\n" +
                "    HoTen    TEXT NOT NULL,\n" +
                "    Password TEXT NOT NULL,\n" +
                "    ChucVu   TEXT DEFAULT ThuThu\n" +
                "                  NOT NULL\n" +
                ")";
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
