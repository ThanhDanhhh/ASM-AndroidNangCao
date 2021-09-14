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

import com.example.androidnangcao_asm_ps13304_dangthanhdanh.Dao.Khoahoc_Dao;
import com.example.androidnangcao_asm_ps13304_dangthanhdanh.Dialog.Bottom_sheetedit_khoahoc;
import com.example.androidnangcao_asm_ps13304_dangthanhdanh.Model.Khoahoc;
import com.example.androidnangcao_asm_ps13304_dangthanhdanh.R;

import java.util.ArrayList;

import static com.example.androidnangcao_asm_ps13304_dangthanhdanh.Fragment.Fragment_sourse.khoahoc_adapters;
import static com.example.androidnangcao_asm_ps13304_dangthanhdanh.Fragment.Fragment_sourse.rv_Khoahoc;

public class Khoahoc_Adapter extends RecyclerView.Adapter<Khoahoc_Adapter.MyViewHolder> {
  private Context context;
  private ArrayList<Khoahoc> ds_khoahoc ;
    Khoahoc_Dao dao;
    public Khoahoc_Adapter(ArrayList<Khoahoc> data, Context context) {
        this.context = context;
        this.ds_khoahoc = data;
    }
    public static class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView tv_makhoahoc,tv_ten_khoahoc;
        public ImageView img_delete_khoahoc,img_edit_khoahoc;
        public MyViewHolder(View v) {
            super(v);
            tv_makhoahoc = v.findViewById(R.id.tv_makhochoc);
            tv_ten_khoahoc = v.findViewById(R.id.tv_tenkhoahoc);
            img_delete_khoahoc = v.findViewById(R.id.img_xoa_khoahoc);
            img_edit_khoahoc = v.findViewById(R.id.img_edit_khoahoc);
        }

    }
    @NonNull
    @Override
    public Khoahoc_Adapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_khoahoc, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.tv_makhoahoc.setText(ds_khoahoc.get(position).getMakhoahoc());
        holder.tv_ten_khoahoc.setText(ds_khoahoc.get(position).getTenkhoahoc());
        holder.img_delete_khoahoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
                builder1.setMessage("Bạn có chắc muốn xóa "+ds_khoahoc.get(position).getTenkhoahoc());
                builder1.setCancelable(true);
                builder1.setPositiveButton(
                        "Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dao = new Khoahoc_Dao(context);
                                dao.delete(ds_khoahoc.get(position).getMakhoahoc());
                                Toast.makeText(context, "Xóa thành công "+ds_khoahoc.get(position).getTenkhoahoc(), Toast.LENGTH_SHORT).show();
                                capnhat();
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
        holder.img_edit_khoahoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle args = new Bundle();
                args.putString("idkhoahoc", ds_khoahoc.get(position).getMakhoahoc()+"");
                args.putString("tenkhoahoc", ds_khoahoc.get(position).getTenkhoahoc()+"");

                Bottom_sheetedit_khoahoc bottom_sheet = new Bottom_sheetedit_khoahoc();
                bottom_sheet.setArguments(args);
                bottom_sheet.show(((AppCompatActivity) context).getSupportFragmentManager(),bottom_sheet.getTag());
            }
        });


    }

    @Override
    public int getItemCount() {
        return ds_khoahoc.size();
    }
    public void capnhat(){
        ds_khoahoc = dao.getall();
        khoahoc_adapters = new Khoahoc_Adapter(ds_khoahoc, context);
        rv_Khoahoc.setAdapter(khoahoc_adapters);

    }
}
