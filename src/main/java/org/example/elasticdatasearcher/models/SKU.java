package org.example.elasticdatasearcher.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "skus")
public class SKU {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Column(name = "sku_code")
    private String sku_code;
    @Column(name = "price")
    private Double price;
    @Column(name = "quantity")
    private Integer quantity;
    @Column(name = "weight")
    private Double weight;
    @Column(name = "created")
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
