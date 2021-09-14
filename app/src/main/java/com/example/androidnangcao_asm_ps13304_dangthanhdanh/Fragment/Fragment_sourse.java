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

import com.example.androidnangcao_asm_ps13304_dangthanhdanh.Adapter.Khoahoc_Adapter;
import com.example.androidnangcao_asm_ps13304_dangthanhdanh.Dao.Khoahoc_Dao;
import com.example.androidnangcao_asm_ps13304_dangthanhdanh.Dialog.Bottom_sheet;
import com.example.androidnangcao_asm_ps13304_dangthanhdanh.Model.Khoahoc;
import com.example.androidnangcao_asm_ps13304_dangthanhdanh.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class Fragment_sourse extends Fragment {
    FloatingActionButton fl_khoahoc;
    public static Khoahoc_Adapter khoahoc_adapters;
    Khoahoc_Dao khoahoc_dao;
    public static RecyclerView rv_Khoahoc;
    ArrayList<Khoahoc> ds_khoahoc;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.activity_fragment_sourse,container,false);
        fl_khoahoc = view.findViewById(R.id.fl_kh);
        rv_Khoahoc = view.findViewById(R.id.rv_kh);
        rv_Khoahoc.setLayoutManager(new LinearLayoutManager(getContext()));
        ds_khoahoc = new ArrayList<>();
        khoahoc_dao = new Khoahoc_Dao(getContext());

        fl_khoahoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bottom_sheet bottom_sheet = new Bottom_sheet();
                bottom_sheet.show(getFragmentManager(),"TAG");
            }
        });
        ds_khoahoc = khoahoc_dao.getall();
        khoahoc_adapters = new Khoahoc_Adapter(ds_khoahoc, getContext());
        rv_Khoahoc.setAdapter(khoahoc_adapters);
        return view;
    }
}