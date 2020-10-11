package com.harera.parsewebsitedata;

import android.graphics.Path;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import Controller.Masrawy;
import Controller.NewsRecyclerViewAdapter;
import Controller.Youm7;

public class MainActivity extends AppCompatActivity {


    private RecyclerView rec_view;
    private NewsRecyclerViewAdapter adapter;
    private List<News> news;
    private Handler handler;
    private Youm7 youm7;
    private Masrawy masrawy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rec_view = findViewById(R.id.rec_view);
        rec_view.setHasFixedSize(true);
        rec_view.setLayoutManager(new LinearLayoutManager(this));

        news = new ArrayList<>();
        adapter = new NewsRecyclerViewAdapter(this, news);
        rec_view.setAdapter(adapter);

        youm7 = new Youm7(news, adapter);
        youm7.execute();

        masrawy = new Masrawy(news, adapter);
        masrawy.execute();


        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                adapter.notifyDataSetChanged();
                MainActivity.this.handler.postDelayed(this, 1000);
            }
        };
        handler = new Handler();
        handler.postDelayed(runnable, 1000);
    }

    @Override
    protected void onStart() {
        super.onStart();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                rec_view.setOnScrollChangeListener(new View.OnScrollChangeListener() {
                    @Override
                    public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                        if (!rec_view.canScrollVertically(View.SCROLL_AXIS_VERTICAL)) {
                            masrawy.getMores();
                            youm7.getMores();
                        }
                    }
                });
            }
        }, 3000);


    }
}