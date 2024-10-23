package org.example.elasticdatasearcher.services;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface LoadingDataService {
    void loadDataToElastic() throws IOException;

    List<Map<String, Object>> filterProducts(String category,Double minPrice, Double maxPrice) throws IOException;
}
