package com.example.androidnangcao_asm_ps13304_dangthanhdanh.Dialog;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

public class Bottom_sheetedit_khoahoc extends BottomSheetDialogFragment {
    EditText edt_edit_makhoahoc,edt_edit_tenkhoahoc;
    Button btn_edt_khoahoc;
    Khoahoc_Dao khoahoc_dao;
    ArrayList<Khoahoc> ds_khoahoc;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bottom_sheet_edit_khoahoc,container,false);
        edt_edit_makhoahoc = view.findViewById(R.id.edt_makhoahoc_edit);
        edt_edit_tenkhoahoc = view.findViewById(R.id.edt_tenkhoahoc_edit);
        btn_edt_khoahoc = view.findViewById(R.id.btn_update_khoahoc);

        Bundle mArgs = getArguments();
        String ma_khoahoc = mArgs.getString("idkhoahoc");
        String ten_khoahoc = mArgs.getString("tenkhoahoc");

        edt_edit_makhoahoc.setText(ma_khoahoc);
        edt_edit_tenkhoahoc.setText(ten_khoahoc);

        btn_edt_khoahoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String ma_khoahocs = (edt_edit_makhoahoc.getText().toString());
                String ten_khoahocs = edt_edit_tenkhoahoc.getText().toString();

                Khoahoc pl = new Khoahoc(ma_khoahocs,ten_khoahocs);
                khoahoc_dao = new Khoahoc_Dao(getActivity());
                khoahoc_dao.update(pl);
                capnhat();
                Toast.makeText(getContext(), "Cập nhật thành công", Toast.LENGTH_SHORT).show();
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
