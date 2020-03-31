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
    private static final String NEWS_URL = "https://www.indiatvnews.com/topic/coronavirus/news";

    public List<NewsModel> getScrapedNews() throws IOException {

        // Get the Document Object Model from the URL
        Document document = Jsoup.connect(NEWS_URL).get();
        var newsList = new ArrayList<NewsModel>();

        for (Element element : document.select("#newsListfull > li ")) {
            var newsModel = new NewsModel();

            newsModel.setNewsHeadline(element.select("> div h3").text());
            newsModel.setNewsBody(element.select("> div p").text());
            newsModel.setNewsMetaData(element.select("> div span").text());

            newsList.add(newsModel);
        }

        return newsList;
    }
}
