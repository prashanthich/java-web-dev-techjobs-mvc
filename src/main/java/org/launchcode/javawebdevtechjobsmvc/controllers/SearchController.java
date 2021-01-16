package org.launchcode.javawebdevtechjobsmvc.controllers;

import org.launchcode.javawebdevtechjobsmvc.models.Job;
import org.launchcode.javawebdevtechjobsmvc.models.JobData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static org.launchcode.javawebdevtechjobsmvc.controllers.ListController.columnChoices;

/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping("search")
public class SearchController {

    @RequestMapping(value = "")
    public String search(Model model) {
        model.addAttribute("columns", columnChoices);
        return "search";
    }

    // TODO #3 - Create a handler to process a search request and render the updated search view.

    @PostMapping("results")
    public String displaySearchResults(Model model, String searchTerm, String searchType) {
        List<Job> jobs = new ArrayList<>();
        if (searchTerm.equalsIgnoreCase("all") || searchTerm.isEmpty()) {
            jobs = JobData.findAll();
        } else {
            jobs = JobData.findByColumnAndValue(searchType, searchTerm);
        }
        model.addAttribute("jobs", jobs);
        model.addAttribute("columns", columnChoices);
        return "search";
    }

}
