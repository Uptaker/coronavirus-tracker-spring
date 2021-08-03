package codes.drinky.coronavirustracker.controllers;

import codes.drinky.coronavirustracker.services.CovidDataService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

@Controller
public class HomeController {

    private final CovidDataService covidDataService;

    public HomeController(CovidDataService covidDataService) {
        this.covidDataService = covidDataService;
    }

    @RequestMapping({"","/"})
    public String home(Model model) {
        model.addAttribute("cases", covidDataService.getAllStats());
        model.addAttribute("lastupdated", covidDataService.getCurrentTime());
        return "home";
    }
}
