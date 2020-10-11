package Controller;

import android.os.AsyncTask;

import com.harera.parsewebsitedata.News;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.List;

public
class Masrawy extends AsyncTask<Void, Void, Void> {


    private List<News> news;
    private NewsRecyclerViewAdapter adapter;
    private Elements elements;
    private Document document;

    public Masrawy(List<News> news, NewsRecyclerViewAdapter adapter) {
        this.news = news;
        this.adapter = adapter;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        String url = "https://www.masrawy.com/Today#Nav-Today";
        try {
            document = Jsoup.connect(url).get();
            elements = document.select("li[class=mix news]").next().next();
            for (Element element : elements) {
                String imgUrl = element.select("img[class=lazy]").attr("data-src");
                String newsUrl = "https://www.masrawy.com/" + element.select("a[class=item]").attr("href");
                String title = element.select("img[class=lazy]").attr("alt");

                news.add(new News(imgUrl, title, newsUrl));
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
            String imgUrl = element.select("img[class=lazy]").attr("data-src");
            String newsUrl = "https://www.masrawy.com/" + element.select("a[class=item]").attr("href");
            String title = element.select("img[class=lazy]").attr("alt");

            news.add(new News(imgUrl, title, newsUrl));
        }


    }

}
