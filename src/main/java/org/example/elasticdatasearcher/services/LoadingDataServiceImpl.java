package org.example.elasticdatasearcher.services;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.IndexRequest;
import co.elastic.clients.elasticsearch.core.IndexResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.elasticdatasearcher.models.Product;
import org.example.elasticdatasearcher.models.SKU;
import org.example.elasticdatasearcher.repos.ProductRepository;
import org.example.elasticdatasearcher.repos.SKURepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class LoadingDataServiceImpl {
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
}
