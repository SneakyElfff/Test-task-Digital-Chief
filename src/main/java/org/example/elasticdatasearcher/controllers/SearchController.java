package org.example.elasticdatasearcher.controllers;

import org.example.elasticdatasearcher.services.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
public class SearchController {
    @Autowired
    private SearchService searchService;

    @GetMapping("/search-products")
    public List<Map<String, Object>> searchProducts(@RequestParam String keyword) throws IOException {
        return searchService.searchProducts(keyword);
    }
}
