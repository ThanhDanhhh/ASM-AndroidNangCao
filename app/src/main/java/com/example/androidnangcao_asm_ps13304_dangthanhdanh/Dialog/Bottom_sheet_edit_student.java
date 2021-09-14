package com.example.androidnangcao_asm_ps13304_dangthanhdanh.Dialog;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.androidnangcao_asm_ps13304_dangthanhdanh.Adapter.Student_Adapter;
import com.example.androidnangcao_asm_ps13304_dangthanhdanh.Dao.Khoahoc_Dao;
import com.example.androidnangcao_asm_ps13304_dangthanhdanh.Dao.Student_Dao;
import com.example.androidnangcao_asm_ps13304_dangthanhdanh.Model.Khoahoc;
import com.example.androidnangcao_asm_ps13304_dangthanhdanh.Model.Student;
import com.example.androidnangcao_asm_ps13304_dangthanhdanh.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.ArrayList;


import static com.example.androidnangcao_asm_ps13304_dangthanhdanh.Fragment.Fragment_Student.rv_sinhvien;

import static com.example.androidnangcao_asm_ps13304_dangthanhdanh.Fragment.Fragment_Student.sinhvien_adapters;


public class Bottom_sheet_edit_student extends BottomSheetDialogFragment {
    EditText edt_edit_masv,edt_edit_tensv,tv_edit_ngaysinh;

    Spinner sp_edit_khoahoc;
    Button btn_update_khoahoc;
    Student_Dao student_dao;
    ArrayList<Khoahoc> ds_khoahoc;
    ArrayList<Student> ds_sinhvien;
    Khoahoc_Dao khoahoc_dao;
    String spinner;
    Student_Adapter student_adapter;
//    Adapter_sp_thu adapterSpThu;
//    int id;
//    String trangthai_;
    String show;
    public Bottom_sheet_edit_student() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bootom_sheet_edit_student, container, false);
        edt_edit_masv = view.findViewById(R.id.edt_editma_sinhvien);
        edt_edit_tensv = view.findViewById(R.id.edt_editten_sinhvien);
        tv_edit_ngaysinh = view.findViewById(R.id.tv_editngaysinh);
      // tv_ngay = view.findViewById(R.id.tv_ngay);
      // tv_trangthais = view.findViewById(R.id.tv_trangthais);
        sp_edit_khoahoc = view.findViewById(R.id.sp_edit_khoahoc);
        btn_update_khoahoc = view.findViewById(R.id.btn_udate_sinhvien);
//
//        Bundle mArgs = getArguments();
//        id = Integer.parseInt(mArgs.getString("MaGD"));
//        String tieu_de = mArgs.getString("Tieude");
//        String ngay = mArgs.getString("Ngay");
//        double tien = mArgs.getDouble("Tien");
//        String mota = mArgs.getString("MoTa");
//        String maloai = mArgs.getString("Maloai");

//        edt_tieude.setText(tieu_de);
//        DecimalFormat formatter = (DecimalFormat) NumberFormat.getInstance(Locale.US);
//        formatter.applyPattern("#,###,###,###");
//        String formattedString = formatter.format(tien);

        final Bundle getupdate = getArguments();
        final String masv =getupdate.getString("MaSV");
        String tensv =getupdate.getString("TenSV");
        String ngaySinh =getupdate.getString("NgaySinh");
        String khoahoc =getupdate.getString("idlop");

        edt_edit_masv.setText(masv);
        edt_edit_tensv.setText(tensv);
        tv_edit_ngaysinh.setText(ngaySinh);
        ds_khoahoc= new ArrayList<>();
        khoahoc_dao= new Khoahoc_Dao(getContext());

        ds_khoahoc=khoahoc_dao.getall();
        ArrayAdapter<Khoahoc> arrayAdapter = new ArrayAdapter<Khoahoc>(getActivity(),android.R.layout.simple_list_item_1,ds_khoahoc);
        sp_edit_khoahoc.setAdapter(arrayAdapter);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_list_item_1);
        for (int i = 0; i < ds_khoahoc.size(); i++) {
            if (ds_khoahoc.get(i).getTenkhoahoc().equals(khoahoc)) {
                sp_edit_khoahoc.setSelection(i);
                break;
            }}


        sp_edit_khoahoc.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                spinner=ds_khoahoc.get(sp_edit_khoahoc.getSelectedItemPosition()).getTenkhoahoc();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btn_update_khoahoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String makh = edt_edit_masv.getText().toString();
                String tenkh = edt_edit_tensv.getText().toString();
                String nganh = tv_edit_ngaysinh.getText().toString();
                String ngay = spinner;
             //   String id = getupdate.getString("id");
                ds_sinhvien= new ArrayList<>();
                student_dao = new Student_Dao(getContext());
                student_dao.updatesv(masv,makh,tenkh,nganh,ngay);
                ds_sinhvien = student_dao.getall();
                Toast.makeText(getActivity(), "Update Thành Công", Toast.LENGTH_SHORT).show();
                sinhvien_adapters =new Student_Adapter(ds_sinhvien,getActivity());
                rv_sinhvien.setAdapter(sinhvien_adapters);
                dismiss();

            }
        });

        return view;
    }


}
