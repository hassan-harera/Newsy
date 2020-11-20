package com.harera.parsewebsitedata;


import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class NewsView extends AppCompatActivity {

    private WebView newsWebView;
    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_view);

        newsWebView = findViewById(R.id.news_web);

        url = getIntent().getExtras().getString("URL");
        
        loadNews();
    }

    private void loadNews() {
        newsWebView.setWebViewClient(new WebViewClient());
        newsWebView.loadUrl(url);
    }
}