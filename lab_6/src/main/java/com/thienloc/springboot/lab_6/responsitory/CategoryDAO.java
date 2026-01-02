package com.thienloc.springboot.lab_6.responsitory;

import com.thienloc.springboot.lab_6.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryDAO extends JpaRepository<Category, String> {
}
