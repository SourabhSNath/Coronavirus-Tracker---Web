package com.sourabh.coronavirustracker.controllers;

import com.sourabh.coronavirustracker.services.CoronaVirusDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

// Not rest controller because it's used to render an UI
@Controller
public class DataController {

    private final CoronaVirusDataService coronaVirusDataService;

    @Autowired
    public DataController(CoronaVirusDataService coronaVirusDataService) {
        this.coronaVirusDataService = coronaVirusDataService;
    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("locationStats", coronaVirusDataService.getAllDataList());
        model.addAttribute("totalReportedCases", coronaVirusDataService.getTotalCases());
        model.addAttribute("totalNewCases", coronaVirusDataService.getTotalNewCases());
        model.addAttribute("topCountries", coronaVirusDataService.getTopCountries());
        return "home";
    }

}