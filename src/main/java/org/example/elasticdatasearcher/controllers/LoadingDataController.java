package org.example.elasticdatasearcher.controllers;

import org.example.elasticdatasearcher.services.LoadingDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
public class LoadingDataController {
    private final LoadingDataService loadingDataService;

    @Autowired
    public LoadingDataController(LoadingDataService loadingDataService) {
        this.loadingDataService = loadingDataService;
    }

    @GetMapping("/load-data")
    public String loadData() throws IOException {
        loadingDataService.loadDataToElastic();

        return "Данные успешно загружены в Elasticsearch";
    }

    @GetMapping("/filter-products")
    public List<Map<String, Object>> filterProducts(
            @RequestParam String category,
            @RequestParam Double minPrice,
            @RequestParam Double maxPrice) throws IOException {

        return loadingDataService.filterProducts(category, minPrice, maxPrice);
    }
}
