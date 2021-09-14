package com.example.androidnangcao_asm_ps13304_dangthanhdanh.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.androidnangcao_asm_ps13304_dangthanhdanh.R;

public class FragmentWebView extends AppCompatActivity {

    WebView wv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_webview);
        wv=findViewById(R.id.webView);
        Intent intent = getIntent();
        String url = intent.getStringExtra("link");
        wv.loadUrl(url);
    }

}
