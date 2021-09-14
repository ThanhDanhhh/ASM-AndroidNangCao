package com.example.androidnangcao_asm_ps13304_dangthanhdanh.Dialog;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.androidnangcao_asm_ps13304_dangthanhdanh.Adapter.Khoahoc_Adapter;
import com.example.androidnangcao_asm_ps13304_dangthanhdanh.Dao.Khoahoc_Dao;
import com.example.androidnangcao_asm_ps13304_dangthanhdanh.Model.Khoahoc;
import com.example.androidnangcao_asm_ps13304_dangthanhdanh.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.ArrayList;

import static com.example.androidnangcao_asm_ps13304_dangthanhdanh.Fragment.Fragment_sourse.khoahoc_adapters;
import static com.example.androidnangcao_asm_ps13304_dangthanhdanh.Fragment.Fragment_sourse.rv_Khoahoc;

public class Bottom_sheet extends BottomSheetDialogFragment {
    EditText edt_tenkhoahoc,edt_makhoahoc;
    Button btn_them;
    Khoahoc_Dao khoahoc_dao;
    ArrayList<Khoahoc> ds_khoahoc;
    public Bottom_sheet(){
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bottom_sheet_khoahoc, container, false);
        edt_makhoahoc = view.findViewById(R.id.edt_ma_khoahoc);
        edt_tenkhoahoc = view.findViewById(R.id.edt_ten_khoahoc);

        btn_them = view.findViewById(R.id.btn_thempl);
        khoahoc_dao = new Khoahoc_Dao(getContext());

//        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
//                R.array.planets_array, android.R.layout.simple_spinner_item);
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        sp_loai.setAdapter(adapter);

        btn_them.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ma_khoahoc = edt_makhoahoc.getText().toString();
                String ten_khoahoc = edt_tenkhoahoc.getText().toString();

                Khoahoc pl = new Khoahoc(ma_khoahoc,ten_khoahoc);

                khoahoc_dao.create(pl);
                capnhat();
                Toast.makeText(getContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                dismiss();
            }
        });


        return view;
    }


    public void capnhat(){
        ds_khoahoc = new ArrayList<>();
        ds_khoahoc = khoahoc_dao.getall();
        khoahoc_adapters = new Khoahoc_Adapter(ds_khoahoc, getContext());
        rv_Khoahoc.setAdapter(khoahoc_adapters);

    }
}
