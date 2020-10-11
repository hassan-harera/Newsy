package Controller;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.ArrayAdapter;

import com.harera.parsewebsitedata.News;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;


public class Youm7 extends AsyncTask<Void, Void, Void> {


    private NewsRecyclerViewAdapter adapter;
    private Elements elements;
    private Document document;

    public Youm7( NewsRecyclerViewAdapter adapter) {
        this.adapter = adapter;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        String url = "https://www.youm7.com/Section/%D8%A3%D8%AE%D8%A8%D8%A7%D8%B1-%D8%B9%D8%A7%D8%AC%D9%84%D8%A9/65/1";
        try {
            document = Jsoup.connect(url).get();
            elements = document.select("div[class=col-xs-12 bigOneSec]");
            for (Element element : elements) {
                String imgUrl = element.select("img[class=img-responsive]").attr("src");
                String newsUrl = "https://www.youm7.com" + element.select("a[class=bigOneImg]").attr("href");
                String title = element.select("img[class=img-responsive]").attr("title");

                adapter.update(new News(imgUrl, title, newsUrl));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void getMores() {
        elements = elements.next();
        int i = 1;
        for (Element element : elements) {
            if(i++ == 1){
                continue;
            }

            String imgUrl = element.select("img[class=img-responsive]").attr("src");
            String newsUrl = "https://www.youm7.com" + element.select("a[class=bigOneImg]").attr("href");
            String title = element.select("img[class=img-responsive]").attr("title");

            adapter.update(new News(imgUrl, title, newsUrl));
        }
    }
}

