package com.sourabh.coronavirustracker.controllers;

import com.sourabh.coronavirustracker.models.NewsModel;
import com.sourabh.coronavirustracker.services.GlobalDataService;
import com.sourabh.coronavirustracker.services.IndianDataService;
import com.sourabh.coronavirustracker.services.NewsScraperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;
import java.util.List;

// Not rest controller because it's used to render an UI
@Controller
public class DataController {

    //    private final CoronaVirusDataService coronaVirusDataService;
    private final GlobalDataService globalDataService;
    private final IndianDataService indianDataService;
    private final NewsScraperService newsScraperService;

    @Autowired
    public DataController(GlobalDataService globalDataService,
                          IndianDataService indianDataService,
                          NewsScraperService newsScraperService) {
        this.globalDataService = globalDataService;
        this.indianDataService = indianDataService;
        this.newsScraperService = newsScraperService;
    }

    @GetMapping("/")
    public String home(Model model) throws IOException {
        model.addAttribute("globalData", globalDataService.getGlobalData());
        model.addAttribute("coronaNews", getNewsService());

        var totalData = globalDataService.getTotalData();
        var recovered = totalData.getRecovered();
        model.addAttribute("totalReported", totalData.getConfirmed());
        model.addAttribute("newCases", totalData.getNewConfirmed());
        model.addAttribute("deaths", totalData.getDeaths());
        model.addAttribute("recovered", (recovered == 0) ? "Unavailable" : recovered);

        return "global";
    }

    @GetMapping("india")
    public String india(Model model) throws IOException {
        var ids = indianDataService.getIndianData();
        model.addAttribute("indianData", ids.subList(1, ids.size()));
        model.addAttribute("totalReported", ids.get(0).getTotalConfirmed());
        model.addAttribute("activeCases", ids.get(0).getCurrentlyActive());
        model.addAttribute("recovered", ids.get(0).getCuredOrMigrated());
        model.addAttribute("deaths", ids.get(0).getDeaths());
        model.addAttribute("coronaNews", getNewsService());
        return "india";
    }

    public List<NewsModel> getNewsService() throws IOException {
        System.out.println("News Service in DataController called");
        return newsScraperService.getScrapedNews();
    }

}