package org.launchcode.javawebdevtechjobsmvc.controllers;

import org.launchcode.javawebdevtechjobsmvc.models.Job;
import org.launchcode.javawebdevtechjobsmvc.models.JobData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping("search")
public class SearchController extends TechJobsController {

    @RequestMapping(value = "")
    public String search(Model model) {
      //  model.addAttribute("columns", columnChoices);
        model.addAttribute("searchType", "all");
        return "search";
    }

    @PostMapping("results")
    public String displaySearchResults(Model model, String searchTerm, String searchType) {
        List<Job> jobs = new ArrayList<>();
        if (searchTerm.equalsIgnoreCase("all") || searchTerm.isEmpty()) {
            jobs = JobData.findAll();
        } else {
            jobs = JobData.findByColumnAndValue(searchType, searchTerm);
        }
        model.addAttribute("jobs", jobs);
      //  model.addAttribute("columns", columnChoices);
        model.addAttribute("searchType", searchType);
        return "search";
    }

}
