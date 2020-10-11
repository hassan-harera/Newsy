package Controller;

import android.os.AsyncTask;

import com.harera.parsewebsitedata.News;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.List;

public class SkyNews extends AsyncTask<Void, Void, Void> {


    private NewsRecyclerViewAdapter adapter;
    private Elements elements;
    private Document document;

    public SkyNews( NewsRecyclerViewAdapter adapter) {
        this.adapter = adapter;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        String url = "https://news.sky.com/";
        try {
            document = Jsoup.connect(url).get();
            elements = document.select("div[class=sdc-site-tiles__item sdc-site-tile sdc-site-tile--has-link]");
            for (Element element : elements) {
                String imgUrl = element.select("img[class=sdc-site-tile__image]").attr("src");
                String newsUrl = "https://news.sky.com/" + element.select("a[class=sdc-site-tile__headline-link]").attr("href");
                String title = element.select("span[class=sdc-site-tile__headline-text]").text();

                adapter.update(new News(imgUrl, title, newsUrl));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void getMores() {
        if (elements == null || document == null) {
            return;
        }

        elements = elements.next();
        for (Element element : elements) {
            String imgUrl = element.select("img[class=sdc-site-tile__image]").attr("src");
            String newsUrl = "https://news.sky.com/" + element.select("a[class=sdc-site-tile__headline-link]").attr("href");
            String title = element.select("span[class=sdc-site-tile__headline-text]").text();

            adapter.update(new News(imgUrl, title, newsUrl));
        }
    }
}