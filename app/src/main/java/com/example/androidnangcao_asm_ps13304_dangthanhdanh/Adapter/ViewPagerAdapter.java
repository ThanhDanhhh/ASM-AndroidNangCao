package com.example.androidnangcao_asm_ps13304_dangthanhdanh.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.androidnangcao_asm_ps13304_dangthanhdanh.Fragment.Fragment_Social;
import com.example.androidnangcao_asm_ps13304_dangthanhdanh.Fragment.Fragment_Student;
import com.example.androidnangcao_asm_ps13304_dangthanhdanh.Fragment.Frangment_Maps;
import com.example.androidnangcao_asm_ps13304_dangthanhdanh.Fragment.Frangment_News;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {
    public ViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new Fragment_Student();
            case 1:
                return new Frangment_Maps();
            case 2:
                return new Frangment_News();
            case 3:
                return new Fragment_Social();
            default:
                return new Fragment_Student();
        }
    }

    @Override
    public int getCount() {
        return 4;
    }
}
