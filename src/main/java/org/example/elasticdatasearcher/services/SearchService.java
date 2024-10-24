package org.example.elasticdatasearcher.services;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface SearchService {
    List<Map<String, Object>> searchProducts(String keyword) throws IOException;
}
