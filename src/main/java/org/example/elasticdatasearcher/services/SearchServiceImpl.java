package org.example.elasticdatasearcher.services;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.SearchRequest;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.search.Hit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class SearchServiceImpl implements SearchService{
    @Autowired
    private ElasticsearchClient elasticsearchClient;

    public List<Map<String, Object>> searchProducts(String keyword) throws IOException {
        SearchRequest searchRequest = SearchRequest.of(s -> s
                .index("market_index")
                .query(q -> q
                        .multiMatch(m -> m
                                .query(keyword)
                                .fields(List.of("product_name", "product_description", "skus.sku_code"))
                        )
                )
        );

        SearchResponse<Map<String, Object>> searchResponse = elasticsearchClient.search(searchRequest, (Type) Map.class);

        return searchResponse.hits().hits().stream()
                .map(Hit::source)
                .collect(Collectors.toList());
    }
}
