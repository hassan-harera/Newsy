package com.harera.parsewebsitedata;

import android.content.Context;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import Controller.NewsRecyclerViewAdapter;

public class Data {

    public static void getNews(Context context, RecyclerView rec_view) {

        rec_view.setHasFixedSize(true);
        rec_view.setLayoutManager(new LinearLayoutManager(context));

        List<News> news = new ArrayList<>();
        RecyclerView.Adapter adapter = new NewsRecyclerViewAdapter(context, news);
        rec_view.setAdapter(adapter);

        String url = "https://www.youm7.com/Section/%D8%A3%D8%AE%D8%A8%D8%A7%D8%B1-%D8%B9%D8%A7%D8%AC%D9%84%D8%A9/65/1";
        try {
            Document document = Jsoup.connect(url).get();
            Elements elements = document.select("div.col-xs-12 bigOneSec");
            for (Element element : elements) {
                String imgUrl = element.select("a.bigOneImg")
                        .select("img")
                        .attr("src");

                String titleUrl = element.select("h3")
                        .select("a")
                        .text();

//                news.add(new News(imgUrl, titleUrl));
                adapter.notifyDataSetChanged();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
