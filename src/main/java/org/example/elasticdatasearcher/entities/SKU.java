package org.example.elasticdatasearcher.entities;

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
}
