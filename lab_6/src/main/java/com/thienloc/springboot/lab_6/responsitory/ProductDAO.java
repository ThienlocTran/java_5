package com.thienloc.springboot.lab_6.responsitory;

import com.thienloc.springboot.lab_6.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductDAO extends JpaRepository<Product, Integer> {
}
