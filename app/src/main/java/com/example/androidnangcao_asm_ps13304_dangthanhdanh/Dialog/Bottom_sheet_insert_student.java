package com.example.androidnangcao_asm_ps13304_dangthanhdanh.Dialog;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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


public class Bottom_sheet_insert_student extends BottomSheetDialogFragment {
    EditText edt_insert_masv,edt_insert_tensv,tv_insert_ngaysinh;
    Spinner sp_sinhvien;
    Button btn_insert_sinhvien;
    Student_Dao sinhvien_dao;
    ArrayList<Khoahoc> ds_khoahoc;
    ArrayList<Student> ds_sinhvien;
    Khoahoc_Dao khoahoc_dao;

    Student_Adapter student_adapter;
//    Adapter_sp_thu adapterSpThu;
//    int id;
//    String trangthai_;

    public Bottom_sheet_insert_student() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bootom_sheet_insert_student, container, false);
        edt_insert_masv = view.findViewById(R.id.edt_insert_ma_sinhvien);
        edt_insert_tensv = view.findViewById(R.id.edt_insert_ten_sinhvien);
        tv_insert_ngaysinh = view.findViewById(R.id.tv_insert_ngaysinh);
      // tv_ngay = view.findViewById(R.id.tv_ngay);
      // tv_trangthais = view.findViewById(R.id.tv_trangthais);
        sp_sinhvien = view.findViewById(R.id.sp_insert_khoahoc);
        btn_insert_sinhvien = view.findViewById(R.id.btn_insert_sinhvien);
//       final Calendar calendar_date = Calendar.getInstance();
//       final int year = calendar_date.get(calendar_date.YEAR);
//       final int month = calendar_date.get(Calendar.MONTH);
//       final  int day = calendar_date.get(calendar_date.DAY_OF_MONTH);
//       tv_insert_ngaysinh.setOnClickListener(new View.OnClickListener() {
//           @Override
//           public void onClick(View view) {
//               DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), view,)
//           }
//       });
        ds_khoahoc = new ArrayList<>();
        khoahoc_dao = new Khoahoc_Dao(getActivity());
        ds_khoahoc = khoahoc_dao.getall();
        ArrayAdapter<Khoahoc> arrayAdapter = new ArrayAdapter<Khoahoc>(getActivity(),android.R.layout.simple_list_item_1,ds_khoahoc);
        sp_sinhvien.setAdapter(arrayAdapter);

        btn_insert_sinhvien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ma_sinhvien = edt_insert_masv.getText().toString();
                String ten_sinhvien = edt_insert_tensv.getText().toString();
                String ngaysinh = tv_insert_ngaysinh.getText().toString();

                int index = sp_sinhvien.getSelectedItemPosition();
                String malop = ds_khoahoc.get(index).getMakhoahoc();

                sinhvien_dao = new Student_Dao(getActivity());
                sinhvien_dao.inserSv(new Student(ma_sinhvien,ten_sinhvien,ngaysinh,malop));
                capnhat();
                Toast.makeText(getContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                dismiss();
            }
        });



        return view;
    }
    public void capnhat(){
        ds_sinhvien = new ArrayList<>();
        sinhvien_dao = new Student_Dao(getActivity());
        ds_sinhvien = sinhvien_dao.getall();
        sinhvien_adapters = new Student_Adapter(ds_sinhvien, getContext());
        rv_sinhvien.setAdapter(sinhvien_adapters);

    }


}
