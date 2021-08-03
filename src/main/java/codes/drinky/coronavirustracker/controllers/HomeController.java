package codes.drinky.coronavirustracker.controllers;

import codes.drinky.coronavirustracker.models.LocationStats;
import codes.drinky.coronavirustracker.services.CovidDataService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.util.List;

@Controller
public class HomeController {

    private final CovidDataService covidDataService;

    public HomeController(CovidDataService covidDataService) {
        this.covidDataService = covidDataService;
    }

    @RequestMapping({"","/"})
    public String home(Model model) {
        List<LocationStats> allStats = covidDataService.getAllStats();
        int totalCases = allStats.stream().mapToInt(stat -> stat.getLatestTotalCases()).sum();
        int totalNewCases = allStats.stream().mapToInt(stat -> stat.getDiffFromPrevDay()).sum();
        model.addAttribute("cases", covidDataService.getAllStats());
        model.addAttribute("lastupdated", covidDataService.getCurrentTime());
        model.addAttribute("totalReportedCases", totalCases);
        model.addAttribute("totalNewCases", totalNewCases);
        return "home";
    }
}
