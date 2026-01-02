package com.thienloc.springboot.lab_6.repository;

import com.thienloc.springboot.lab_6.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDAO extends JpaRepository<Order, Long> {
}