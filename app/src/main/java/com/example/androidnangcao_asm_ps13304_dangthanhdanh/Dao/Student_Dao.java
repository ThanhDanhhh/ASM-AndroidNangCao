package com.example.androidnangcao_asm_ps13304_dangthanhdanh.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.androidnangcao_asm_ps13304_dangthanhdanh.Database.DbHelper;
import com.example.androidnangcao_asm_ps13304_dangthanhdanh.Model.Student;

import java.util.ArrayList;

public class Student_Dao {
    DbHelper helper2;
    SQLiteDatabase helper;
    public Student_Dao(Context context){
        helper2 = new DbHelper(context);
    }

    public ArrayList<Student> getall() {
        ArrayList<Student> data = new ArrayList<>();
        helper = helper2.getWritableDatabase();
        Cursor cursor = helper.rawQuery("SELECT*FROM SINH_VIEN", null);
        if (cursor.moveToFirst()) {
            do {

                String maSv =cursor.getString(0);
                String makhoahoc= cursor.getString(1);
                String tenSv = cursor.getString(2);
                String ngaysinh = cursor.getString(3);
                data.add(new Student(maSv, makhoahoc, tenSv, ngaysinh));
            } while (cursor.moveToNext());
        }
        return data;
    }
//    public boolean create(Student sv){
//            SQLiteDatabase db = helper2.getWritableDatabase();
//            ContentValues values = new ContentValues();
//            values.put("MaSV", sv.getMasv());
//            values.put("TenSV", sv.getTensv());
//            values.put("NgaySinh", sv.getNgaysinh());
//            values.put("idlop", sv.getMalop());
//            long row = db.insert("SINH_VIEN", null,values);
//            if(row > 0 ){
//                return true;
//            }else {
//                return false;
//            }
//    }
    public boolean inserSv(Student sv) {
        helper = helper2.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("MaSV", sv.getMasv());
        values.put("TenSV", sv.getTensv());
        values.put("NgaySinh", sv.getNgaysinh());
        values.put("idkhoahoc", sv.getMalop());
        long kaka = helper.insert("SINH_VIEN", null, values);
        return  kaka>0;
    }
    public boolean updatesv(String id, String masv, String tensv, String mssv, String lop){
        SQLiteDatabase db = helper2.getWritableDatabase();
        ContentValues values = new ContentValues();
        //values.put("MaSV", masv);
        values.put("TenSV", tensv);
        values.put("NgaySinh", mssv);
        values.put("idkhoahoc", lop);

        long row = db.update("SINH_VIEN",values,"MaSV=?",new String[]{masv});
        return row>0;
    }
    public boolean delete(String ten){
        SQLiteDatabase db = helper2.getWritableDatabase();
        int row = db.delete("SINH_VIEN", "TenSV=?", new String[]{ten});
        return row>0;
    }
}
