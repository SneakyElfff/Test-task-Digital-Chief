package org.example.elasticdatasearcher.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;
    @Column(name = "category")
    private String category;
    @Column(name = "description")
    private String description;
    @Column(name = "created")
    private LocalDateTime created = LocalDateTime.now();

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getCreated() {
        return created;
    }
}
