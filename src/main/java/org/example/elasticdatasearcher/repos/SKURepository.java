package org.example.elasticdatasearcher.repos;

import org.example.elasticdatasearcher.models.SKU;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SKURepository extends JpaRepository<SKU, Long> {
    List<SKU> findByProductId(Long productID);
}
