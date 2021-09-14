package com.example.androidnangcao_asm_ps13304_dangthanhdanh.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DbHelper extends SQLiteOpenHelper {
    public DbHelper(Context context) {
        super(context,"hello.db",null,2);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        try{
//            db.execSQL("CREATE TABLE quantri(username text primary key , password text)");
//            db.execSQL(" INSERT INTO quantri VALUES ('admin','123456') ");

            db.execSQL("CREATE TABLE KHOA_HOC(idkhoahoc text primary key, tenkhoahoc text)");
            db.execSQL("INSERT INTO KHOA_HOC VALUES ('LT15305', 'LỚP Mobile 153.3 4')");
            db.execSQL("INSERT INTO KHOA_HOC VALUES ('UD15305', 'LỚP UDPM 153.3 4')");

            db.execSQL("CREATE TABLE SINH_VIEN(MaSV text primary key, "+
                    "TenSV text, NgaySinh date"+",idkhoahoc text references KHOA_HOC(idkhoahoc))");
            db.execSQL("INSERT INTO SINH_VIEN VALUES (1, 'Ngoc', '30/7', 'LT15305')");


        } catch (Exception e){
            Log.d("Loi tai day", e+"");
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        db.execSQL(" DROP TABLE IF EXISTS quantri ");
        db.execSQL(" DROP TABLE IF EXISTS KHOA_HOC ");
        db.execSQL(" DROP TABLE IF EXISTS SINH_VIEN ");
    }
}
