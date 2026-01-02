package com.thienloc.springboot.lab_6.repository;

import com.thienloc.springboot.lab_6.entity.Category;
import com.thienloc.springboot.lab_6.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductDAO extends JpaRepository<Product, Integer>
{
// JPQL
//    @Query("FROM Product o WHERE o.price BETWEEN ?1 AND ?2")
//    List<Product> findByPrice(double minPrice, double maxPrice);
//    @Query("FROM Product o WHERE o.name LIKE ?1")
//    Page<Product> findByKeywords(String keywords, Pageable pageable);

    //DSL
    // Tìm kiếm theo khoảng giá
    List<Product> findByPriceBetween(Double minPrice, Double maxPrice);
    
//    // Tìm kiếm theo từ khóa với phân trang
//    @Query("FROM Product p WHERE p.name LIKE ?1")
//    Page<Product> findByKeywords(String keywords, Pageable pageable);

    Page<Product> findAllByNameLike(String keywords, Pageable pageable);

    // Tồn kho
    @Query("SELECT o.category AS group, sum(o.price) AS sum, count(o) AS count "

            + " FROM Product o "
            + " GROUP BY o.category"
            + " ORDER BY sum(o.price) DESC")
    List<Report> getInventoryByCategory();


}