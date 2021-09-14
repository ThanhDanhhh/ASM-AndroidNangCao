package com.example.androidnangcao_asm_ps13304_dangthanhdanh.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.androidnangcao_asm_ps13304_dangthanhdanh.Database.DbHelper;
import com.example.androidnangcao_asm_ps13304_dangthanhdanh.Model.Khoahoc;

import java.util.ArrayList;

public class Khoahoc_Dao {
    DbHelper helper;
    SQLiteDatabase db1;
    public Khoahoc_Dao(Context context){
        helper = new DbHelper(context);
    }

    public ArrayList<Khoahoc> getall() {
        ArrayList<Khoahoc> data = new ArrayList<>();
        db1 = helper.getWritableDatabase();
        Cursor cursor = db1.rawQuery("SELECT*FROM KHOA_HOC", null);
        if (cursor.moveToFirst()) {
            do {

                String makhoahoc = cursor.getString(0);
                String tenkhoahoc = cursor.getString(1);

                data.add(new Khoahoc(makhoahoc, tenkhoahoc));
            } while (cursor.moveToNext());
        }
        return data;

    }
    //insert du lieu
    public void create(Khoahoc khoahoc){
        db1= helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("idkhoahoc", khoahoc.getMakhoahoc());
        values.put("tenkhoahoc", khoahoc.getTenkhoahoc());
        db1.insert("KHOA_HOC", null,values);
//        return row>0;
    }
    public void update(Khoahoc khoahoc){
        db1 = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
       // values.put("idkhoahoc", khoahoc.getMakhoahoc());
        values.put("tenkhoahoc", khoahoc.getTenkhoahoc());
        db1.update("KHOA_HOC", values, "idkhoahoc=?", new String[]{khoahoc.getMakhoahoc()+""});


    }
    public void delete(String id){
        db1 = helper.getWritableDatabase();
        db1.delete("KHOA_HOC", "idkhoahoc=?", new String[]{id});

    }
}
