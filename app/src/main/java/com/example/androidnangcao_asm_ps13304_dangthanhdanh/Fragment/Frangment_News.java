package com.example.androidnangcao_asm_ps13304_dangthanhdanh.Fragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.androidnangcao_asm_ps13304_dangthanhdanh.Adapter.FeedAdapter;
import com.example.androidnangcao_asm_ps13304_dangthanhdanh.Common.HTTPDataHandler;
import com.example.androidnangcao_asm_ps13304_dangthanhdanh.Model.Item;
import com.example.androidnangcao_asm_ps13304_dangthanhdanh.Model.RSSObject;
import com.example.androidnangcao_asm_ps13304_dangthanhdanh.R;
import com.google.gson.Gson;

import java.util.ArrayList;

public class Frangment_News extends Fragment {
    ActionBar toolbar;
    RecyclerView recyclerView;
    private ArrayList<Item> arrayItem = new ArrayList<>();
    private FeedAdapter feedAdapter;
    public final String RSS_link = "https://vnexpress.net/rss/suc-khoe.rss";
    private final   String RSS_to_Json_API = "https://api.rss2json.com/v1/api.json?rss_url=";

    private ProgressDialog mDialog;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_frangment__news, container, false);
        toolbar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        toolbar.setTitle("News");
        recyclerView = view .findViewById(R.id.rvNews);
        setHasOptionsMenu(true);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(0, toolbar.getHeight(), 0, 0);
        view.setLayoutParams(layoutParams);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
            recyclerView.setLayoutManager(linearLayoutManager);
        feedAdapter = new FeedAdapter(arrayItem);
        recyclerView.setAdapter(feedAdapter);

        mDialog = new ProgressDialog(getContext());
        mDialog.setMessage("Chờ chút ");
        mDialog.setCancelable(false);

        new loadRSSAsync().execute(RSS_to_Json_API+RSS_link);


        return view;
    }

    private class loadRSSAsync extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            // mDialog.isShowing();
            mDialog.show();
        }

        @Override
        protected String doInBackground(String... params) {
            String result;
            HTTPDataHandler http = new HTTPDataHandler();
            result = http.GetHTTPData(params[0]);
            return  result;
        }

        @Override
        protected void onPostExecute(String s) {
            RSSObject rssObject = new Gson().fromJson(s,RSSObject.class);

            arrayItem.clear();
            arrayItem.addAll(rssObject.getItems());
            feedAdapter.notifyDataSetChanged();
            if(mDialog.isShowing())

                // mDialog.isShowing();
                mDialog.dismiss();
        }
    }


    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.news_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.ic_request) {
            new loadRSSAsync().execute(RSS_to_Json_API+RSS_link);
        }
        return true;
    }
}