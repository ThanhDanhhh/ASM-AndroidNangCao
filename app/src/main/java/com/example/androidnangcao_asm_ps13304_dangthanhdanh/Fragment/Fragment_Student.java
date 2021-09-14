package com.example.androidnangcao_asm_ps13304_dangthanhdanh.Fragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.androidnangcao_asm_ps13304_dangthanhdanh.Adapter.Student_Adapter;
import com.example.androidnangcao_asm_ps13304_dangthanhdanh.Dao.Khoahoc_Dao;
import com.example.androidnangcao_asm_ps13304_dangthanhdanh.Dao.Student_Dao;
import com.example.androidnangcao_asm_ps13304_dangthanhdanh.Dialog.Bottom_sheet;
import com.example.androidnangcao_asm_ps13304_dangthanhdanh.Dialog.Bottom_sheet_insert_student;
import com.example.androidnangcao_asm_ps13304_dangthanhdanh.Model.Student;
import com.example.androidnangcao_asm_ps13304_dangthanhdanh.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class Fragment_Student extends Fragment {
    FloatingActionButton fl_sinhvien;
    public static Student_Adapter sinhvien_adapters;
    Student_Dao sinhvien_dao;
    public static RecyclerView rv_sinhvien;
    ArrayList<Student> ds_sinhvien;
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_fragment__student, container, false);
        fl_sinhvien = view.findViewById(R.id.fl_sv);
        rv_sinhvien = view.findViewById(R.id.rv_sv);
        sinhvien_dao = new Student_Dao(getContext());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        rv_sinhvien.setLayoutManager(new LinearLayoutManager(getContext()));
        ds_sinhvien = new ArrayList<>();

        ds_sinhvien = sinhvien_dao.getall();
        sinhvien_adapters =new Student_Adapter(ds_sinhvien,getActivity());
        rv_sinhvien.setAdapter(sinhvien_adapters);

        fl_sinhvien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bottom_sheet_insert_student bottom_sheet = new Bottom_sheet_insert_student();
                bottom_sheet.show(getFragmentManager(),"TAG");
            }
        });

        return view;
    }
    }