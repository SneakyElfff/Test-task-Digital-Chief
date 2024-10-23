package org.example.elasticdatasearcher.repos;

import org.example.elasticdatasearcher.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
