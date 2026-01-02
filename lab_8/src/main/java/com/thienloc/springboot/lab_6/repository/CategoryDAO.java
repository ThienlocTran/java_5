package com.thienloc.springboot.lab_6.repository;

import com.thienloc.springboot.lab_6.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryDAO extends JpaRepository<Category, String> {
}