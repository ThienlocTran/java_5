package com.thienloc.springboot.lab_6.repository;

import com.thienloc.springboot.lab_6.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductDAO extends JpaRepository<Product, Integer> {
}