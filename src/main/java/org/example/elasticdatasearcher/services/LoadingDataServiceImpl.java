package org.example.elasticdatasearcher.services;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.IndexRequest;
import co.elastic.clients.elasticsearch.core.IndexResponse;
import co.elastic.clients.elasticsearch.core.SearchRequest;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.search.Hit;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.elasticdatasearcher.models.Product;
import org.example.elasticdatasearcher.models.SKU;
import org.example.elasticdatasearcher.repos.ProductRepository;
import org.example.elasticdatasearcher.repos.SKURepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class LoadingDataServiceImpl implements LoadingDataService {
    private static final Logger log = LogManager.getLogger(LoadingDataServiceImpl.class);

    @Autowired
    private ProductRepository prodRepo;

    @Autowired
    private SKURepository skuRepo;

    @Autowired
    private ElasticsearchClient elasticsearchClient;

    public void loadDataToElastic() throws IOException {
        List<Product> products = prodRepo.findAll();

        for (Product product : products) {
            List<SKU> skus = skuRepo.findByProductId(product.getId());

            Map<String, Object> document = new HashMap<>();

            document.put("product_id", product.getId());
            document.put("product_name", product.getName());
            document.put("product_category", product.getCategory());
            document.put("product_description", product.getDescription());
            document.put("product_created", product.getCreated());

            document.put("skus", skus.stream().map(sku -> {
                Map<String, Object> skuData = new HashMap<>();
                skuData.put("sku_id", sku.getId());
                skuData.put("sku_code", sku.getSku_code());
                skuData.put("sku_price", sku.getPrice());
                skuData.put("sku_quantity", sku.getQuantity());
                skuData.put("sku_weight", sku.getWeight());
                skuData.put("sku_created", sku.getCreated());

                return skuData;
            }).toList());

            IndexRequest<Map<String, Object>> request = IndexRequest.of(i -> i
                    .index("market_index")
                    .id(product.getId().toString())
                    .document(document)
            );

            IndexResponse response = elasticsearchClient.index(request);

            log.info("Загружен документ {}", response.id());
        }
    }

    public List<Map<String, Object>> filterProducts(String category,Double minPrice, Double maxPrice) throws IOException {
        SearchRequest searchRequest = SearchRequest.of(s -> s
                .index("market_index")
                .query(q -> q
                        .bool(b -> b
                                .must(m -> m
                                        .term(t -> t
                                                .field("product_category.keyword")
                                                .value(category)
                                        )
                                )
                                .must(m -> m
                                        .nested(n -> n
                                                .path("skus")
                                                .query(nq -> nq
                                                        .range(r -> r
                                                                .number(nm -> nm
                                                                        .field("skus.sku_price")
                                                                        .gte(minPrice)
                                                                        .lte(maxPrice)
                                                                )
                                                        )
                                                )
                                        )
                                )
                        )
                )
        );

        SearchResponse<Map<String, Object>> searchResponse = elasticsearchClient.search(searchRequest, (Type) Map.class);

        return searchResponse.hits().hits().stream()
                .map(Hit::source)
                .collect(Collectors.toList());
    }
}
