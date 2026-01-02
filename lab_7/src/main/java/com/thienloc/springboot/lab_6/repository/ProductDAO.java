package com.thienloc.springboot.lab_6.repository;

import com.thienloc.springboot.lab_6.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductDAO extends JpaRepository<Product, Integer> {
    // Tìm kiếm theo khoảng giá
    List<Product> findByPriceBetween(Double minPrice, Double maxPrice);
    
    // Tìm kiếm theo từ khóa với phân trang
    @Query("FROM Product p WHERE p.name LIKE ?1")
    Page<Product> findByKeywords(String keywords, Pageable pageable);
}