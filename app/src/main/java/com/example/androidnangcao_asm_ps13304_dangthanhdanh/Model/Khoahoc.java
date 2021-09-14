package com.example.androidnangcao_asm_ps13304_dangthanhdanh.Model;

public class Khoahoc {
    private String makhoahoc;
    private String tenkhoahoc;

    public Khoahoc(String makhoahoc, String tenkhoahoc) {
        this.makhoahoc = makhoahoc;
        this.tenkhoahoc = tenkhoahoc;
    }


    public String getMakhoahoc() {
        return makhoahoc;
    }

    public void setMakhoahoc(String makhoahoc) {
        this.makhoahoc = makhoahoc;
    }

    public String getTenkhoahoc() {
        return tenkhoahoc;
    }

    public void setTenkhoahoc(String tenkhoahoc) {
        this.tenkhoahoc = tenkhoahoc;
    }
    @Override
    public String toString() {
        return getTenkhoahoc();
    }
}



