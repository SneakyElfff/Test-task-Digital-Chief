package org.example.elasticdatasearcher.controllers;

import org.example.elasticdatasearcher.services.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Controller
public class SearchController {
    @Autowired
    private SearchService searchService;

    @GetMapping("/search-products")
    public String searchPage() {
        return "searchProducts";
    }

    @GetMapping("/search-products-results")
    public String searchProducts(@RequestParam String keyword, Model model) throws IOException {
        List<Map<String, Object>> searchResults = searchService.searchProducts(keyword);

        model.addAttribute("results", searchResults);
        model.addAttribute("keyword", keyword);

        return "searchResults";
    }
}
