package com.example.androidnangcao_asm_ps13304_dangthanhdanh;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Build;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.androidnangcao_asm_ps13304_dangthanhdanh.Fragment.Fragment_Home;
import com.example.androidnangcao_asm_ps13304_dangthanhdanh.Fragment.Fragment_Social;
import com.example.androidnangcao_asm_ps13304_dangthanhdanh.Fragment.Fragment_Student;
import com.example.androidnangcao_asm_ps13304_dangthanhdanh.Fragment.Fragment_sourse;
import com.example.androidnangcao_asm_ps13304_dangthanhdanh.Fragment.Frangment_Maps;
import com.example.androidnangcao_asm_ps13304_dangthanhdanh.Fragment.Frangment_News;
import com.google.android.material.navigation.NavigationView;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MainActivity extends AppCompatActivity {
    DrawerLayout dr_ly;
    Toolbar tb;
    NavigationView nv;
    ActionBarDrawerToggle drawerToggle;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        getWindow().setStatusBarColor(ContextCompat.getColor(MainActivity.this, R.color.colorAccent));

        try {
            PackageInfo info = getPackageManager().getPackageInfo("com.example.androidnangcao_asm_ps13304_dangthanhdanh", PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {

        } catch (NoSuchAlgorithmException e) {

        }
        dr_ly = findViewById(R.id.dr_ly);
        tb = findViewById(R.id.tg_bar);
        nv = findViewById(R.id.nv_view);
        setSupportActionBar(tb);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        drawerToggle =setupDrawerToogle();
    isPermissionGranted();
        drawerToggle.setDrawerIndicatorEnabled(true);
        drawerToggle.syncState();
        dr_ly.addDrawerListener(drawerToggle);
        if (savedInstanceState == null){
            nv.setCheckedItem(R.id.home);
            getSupportFragmentManager().beginTransaction().replace(R.id.fr_ly, new Fragment_Home()).commit();
        }

        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                Fragment fragments = null;
//                Class fragmentClass = null;
                switch (item.getItemId()){
                    case R.id.home:
//                        fragmentClass = Fragment_thu.class;
                        Toast.makeText(MainActivity.this, "Đây là : Trang Chủ!", Toast.LENGTH_SHORT).show();

                        getSupportFragmentManager().beginTransaction().replace(R.id.fr_ly, new Fragment_Home()).commit();
                        break;
                    case R.id.khoahoc:
                        // fragmentClass = Fragment_chi.class;
                        Toast.makeText(MainActivity.this, "Đây là : Sourse!", Toast.LENGTH_SHORT).show();

                        getSupportFragmentManager().beginTransaction().replace(R.id.fr_ly, new Fragment_sourse()).commit();
                        break;

                    case R.id.social:
                        Toast.makeText(MainActivity.this, "Đây là : Social!", Toast.LENGTH_SHORT).show();

                        //fragmentClass = Fragment_thongke.class;
                        getSupportFragmentManager().beginTransaction().replace(R.id.fr_ly, new Fragment_Social()).commit();
                        break;
                    case R.id.news:
                        Toast.makeText(MainActivity.this, "Đây là : News!", Toast.LENGTH_SHORT).show();
                         getSupportFragmentManager().beginTransaction().replace(R.id.fr_ly, new Frangment_News()).commit();
                        break;
                    case R.id.maps:
                        Toast.makeText(MainActivity.this, "Đây là : Maps", Toast.LENGTH_SHORT).show();
                        getSupportFragmentManager().beginTransaction().replace(R.id.fr_ly, new Frangment_Maps()).commit();
                        break;
                    case R.id.student:
                        // fragmentClass = Fragment_chi.class;
                        Toast.makeText(MainActivity.this, "Đây là : Student!", Toast.LENGTH_SHORT).show();
                        getSupportFragmentManager().beginTransaction().replace(R.id.fr_ly, new Fragment_Student()).commit();
                        break;
                    case R.id.caidat:
                        Toast.makeText(MainActivity.this, "Đây là : Sitting!", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.thoat:
                        Toast.makeText(MainActivity.this, "Đây là : Thoát!", Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fr_ly, new Fragment_sourse()).commit();
                }
//                try {
//                    fragments = (Fragment) fragmentClass.newInstance();
//                } catch (Exception e){
//                    e.printStackTrace();
//                }
//                FragmentManager fragmentManager = getSupportFragmentManager();
//                fragmentManager.beginTransaction().replace(R.id.fr_ly, fragments).commit();

                item.setChecked(true);
                setTitle(item.getTitle());
                dr_ly.closeDrawers();
                return true;
            }
        });

    }

    private ActionBarDrawerToggle setupDrawerToogle(){
        return new ActionBarDrawerToggle(MainActivity.this, dr_ly,tb, R.string.OPEN,R.string.CLOSE);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (drawerToggle.onOptionsItemSelected(item)){
            return true;
        }

        return super.onOptionsItemSelected(item);

    }
    public  boolean isPermissionGranted() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(android.Manifest.permission.ACCESS_FINE_LOCATION)
                    == PackageManager.PERMISSION_GRANTED
                    && checkSelfPermission(android.Manifest.permission.ACCESS_FINE_LOCATION)
                    == PackageManager.PERMISSION_GRANTED) {
                Log.v("TAG","Permission is granted");
                return true;
            } else {

                Log.v("TAG","Permission is revoked");
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 2);
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, 2);
                return false;
            }
        }
        else { //permission is automatically granted on sdk<23 upon installation
            Log.v("TAG","Permission is granted");
            return true;
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {




        if (grantResults.length > 0
                && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(getApplicationContext(), "Permission granted", Toast.LENGTH_SHORT).show();
            //do your specific task after read phone state granted
        } else {
            Toast.makeText(getApplicationContext(), "Permission denied", Toast.LENGTH_SHORT).show();
        }
        return;
    }
    @SuppressLint("MissingSuperCall")
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        //super.onActivityResult(requestCode, resultCode, data);
        try {
            for (Fragment fragment : getSupportFragmentManager().getFragments()) {
                fragment.onActivityResult(requestCode, resultCode, data);
                Log.d("Activity", "ON RESULT CALLED");
            }
        } catch (Exception e) {
            Log.d("ERROR", e.toString());
        }
    }
}