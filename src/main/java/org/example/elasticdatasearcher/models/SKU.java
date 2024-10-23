package org.example.elasticdatasearcher.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class SKU {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    private String sku_code;
    private Double price;
    private Integer quantity;
    private Double weight;
    private LocalDateTime created = LocalDateTime.now();

    public Long getId() {
        return id;
    }

    public Product getProduct() {
        return product;
    }

    public String getSku_code() {
        return sku_code;
    }

    public Double getPrice() {
        return price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Double getWeight() {
        return weight;
    }

    public LocalDateTime getCreated() {
        return created;
    }
}
