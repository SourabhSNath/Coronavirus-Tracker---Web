package com.sourabh.coronavirustracker.services;

import com.sourabh.coronavirustracker.models.NewsModel;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class NewsScraperService {
    //    private static final String NEWS_URL = "https://www.indiatvnews.com/topic/coronavirus";
    private static final String NEWS_URL = "https://www.indiatvnews.com/coronavirus";

    public List<NewsModel> getScrapedNews() throws IOException {

        // Get the Document Object Model from the URL
        Document document = Jsoup.connect(NEWS_URL).get();
        var newsList = new ArrayList<NewsModel>();

        for (Element element : document.select("div.topNews.news-cov-body > ul > li > div ")) {
            var newsModel = new NewsModel();

            newsModel.setNewsHeadline(element.select("h3").text());
            newsModel.setNewsBody(element.select("p").text());
            var meta = element.select("span").text();
            var dividerPos = meta.indexOf("|");
            meta = meta.substring(dividerPos + 2);
            newsModel.setNewsMetaData(meta);

            newsList.add(newsModel);
        }

        return newsList;
    }
}
