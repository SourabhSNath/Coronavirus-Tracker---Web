package com.sourabh.coronavirustracker.controllers;

import com.sourabh.coronavirustracker.services.CoronaVirusDataService;
import com.sourabh.coronavirustracker.services.IndianDataService;
import com.sourabh.coronavirustracker.services.NewsScraperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;

// Not rest controller because it's used to render an UI
@Controller
public class DataController {

    private final CoronaVirusDataService coronaVirusDataService;
    private final IndianDataService indianDataService;
    private final NewsScraperService newsScraperService;

    @Autowired
    public DataController(CoronaVirusDataService coronaVirusDataService,
                          IndianDataService indianDataService,
                          NewsScraperService newsScraperService) {
        this.coronaVirusDataService = coronaVirusDataService;
        this.indianDataService = indianDataService;
        this.newsScraperService = newsScraperService;
    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("locationStats", coronaVirusDataService.getAllDataList());
        model.addAttribute("totalReportedCases", coronaVirusDataService.getTotalCases());
        model.addAttribute("totalNewCases", coronaVirusDataService.getTotalNewCases());
        model.addAttribute("topCountries", coronaVirusDataService.getTopCountries());
        return "home";
    }

    @GetMapping("india")
    public String india(Model model) throws IOException {
        model.addAttribute("indianData", indianDataService.getIndianData()
                .subList(1, indianDataService.getIndianData().size()));
        model.addAttribute("coronaNews", newsScraperService.getScrapedNews());
        return "india";
    }

}