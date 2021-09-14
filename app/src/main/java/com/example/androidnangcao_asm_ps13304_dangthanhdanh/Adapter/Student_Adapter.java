package com.example.androidnangcao_asm_ps13304_dangthanhdanh.Adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidnangcao_asm_ps13304_dangthanhdanh.Dao.Student_Dao;
import com.example.androidnangcao_asm_ps13304_dangthanhdanh.Dialog.Bottom_sheet_edit_student;
import com.example.androidnangcao_asm_ps13304_dangthanhdanh.Model.Student;
import com.example.androidnangcao_asm_ps13304_dangthanhdanh.R;

import java.util.ArrayList;

import static com.example.androidnangcao_asm_ps13304_dangthanhdanh.Fragment.Fragment_Student.sinhvien_adapters;
import static com.example.androidnangcao_asm_ps13304_dangthanhdanh.Fragment.Fragment_Student.rv_sinhvien;
public class Student_Adapter extends RecyclerView.Adapter<Student_Adapter.MyViewHolder> {
    private ArrayList<Student> ds_sinhvien;
    private Context context;
    Student_Dao student_dao;

    public Student_Adapter(ArrayList<Student> ds_thu, Context context) {
        this.ds_sinhvien = ds_thu;
        this.context = context;
    }
    public static class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView tv_ma_sv;
        public TextView tv_ngay;
        public TextView tv_khoahoc;
        public TextView tv_ten_sv;
        private ImageView img_xoa_sv;
        private ImageView img_edit_sv;

        public MyViewHolder(View v) {
            super(v);
            tv_ma_sv = v.findViewById(R.id.tv_ma_sinhvien);
            tv_ten_sv = v.findViewById(R.id.tv_sinhvien);
            tv_ngay = v.findViewById(R.id.tv_Ngaysinh);
            tv_khoahoc = v.findViewById(R.id.tv_khochoc);
            img_xoa_sv = v.findViewById(R.id.img_xoa_sv);
            img_edit_sv = v.findViewById(R.id.img_edit_sv);
        }
    }
    @Override
    public Student_Adapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_student, parent, false);
        Student_Adapter.MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }
    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.tv_ma_sv.setText(ds_sinhvien.get(position).getMasv());
        holder.tv_ngay.setText(ds_sinhvien.get(position).getNgaysinh()+"");
        holder.tv_ten_sv.setText(ds_sinhvien.get(position).getTensv());
        holder.tv_khoahoc.setText(ds_sinhvien.get(position).getMalop());
        //Dinh dang hien thi so tien
//        DecimalFormat formatter = new DecimalFormat("#,###");
//        String s = formatter.format(ds_thu.get(position).getTien());
//        holder.tv_tien.setText(s);
        holder.img_xoa_sv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
                builder1.setMessage("Bạn có chắc muốn xóa "+ds_sinhvien.get(position).getMasv());
                builder1.setCancelable(true);
                builder1.setPositiveButton(
                        "Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                student_dao = new Student_Dao(context);
                                student_dao.delete(ds_sinhvien.get(position).getMasv());
                                Toast.makeText(context, "Xóa thành công "+ds_sinhvien.get(position).getMasv(), Toast.LENGTH_SHORT).show();
                                ds_sinhvien.remove(position);
                                notifyDataSetChanged();
                                dialog.cancel();
                            }
                        });

                builder1.setNegativeButton(
                        "No",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

                AlertDialog alert11 = builder1.create();
                alert11.show();
            }
        });

        holder.img_edit_sv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle args = new Bundle();
                args.putString("MaSV", ds_sinhvien.get(position).getMasv()+"");
                args.putString("TenSV", ds_sinhvien.get(position).getTensv()+"");
                args.putString("NgaySinh", ds_sinhvien.get(position).getNgaysinh()+"");
                args.putString("idlop", ds_sinhvien.get(position).getMalop()+"");
                student_dao = new Student_Dao(context);
//                String th = student_dao.get_trangthai(ds_thu.get(position).getMaGD(),ds_thu.get(position).getMaloai());
//                args.putString("trangthai", th);

                Bottom_sheet_edit_student bottom_sheet = new Bottom_sheet_edit_student();
                //bottom_sheet.show(((AppCompatActivity)context).getSupportFragmentManager(),"TAG");
                bottom_sheet.setArguments(args);
                bottom_sheet.show(((AppCompatActivity) context).getSupportFragmentManager(),bottom_sheet.getTag());

            }
        });

    }
    @Override
    public int getItemCount() {
        return ds_sinhvien.size();
    }

    public void capnhat(){
         ds_sinhvien = new ArrayList<>();
        ds_sinhvien = student_dao.getall();
        sinhvien_adapters = new Student_Adapter(ds_sinhvien, context);
        rv_sinhvien.setAdapter(sinhvien_adapters);
    }


}
