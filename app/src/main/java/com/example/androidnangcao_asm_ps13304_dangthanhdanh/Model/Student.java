package com.example.androidnangcao_asm_ps13304_dangthanhdanh.Model;

public class Student {
    private String masv;
    private String tensv;
    private String ngaysinh;
    private String malop;

    public Student(String masv, String tensv, String ngaysinh, String malop) {
        this.masv = masv;
        this.tensv = tensv;
        this.ngaysinh = ngaysinh;
        this.malop = malop;
    }

//    public Student(String ma_sinhvien, String ten_sinhvien, String ngaysinh) {
//    }

    public String getMasv() {
        return masv;
    }

    public void setMasv(String masv) {
        this.masv = masv;
    }

    public String getTensv() {
        return tensv;
    }

    public void setTensv(String tensv) {
        this.tensv = tensv;
    }

    public String getNgaysinh() {
        return ngaysinh;
    }

    public void setNgaysinh(String ngaysinh) {
        this.ngaysinh = ngaysinh;
    }

    public String getMalop() {
        return malop;
    }

    public void setMalop(String malop) {
        this.malop = malop;
    }

    @Override
    public String toString() {
        return getTensv();

    }
}