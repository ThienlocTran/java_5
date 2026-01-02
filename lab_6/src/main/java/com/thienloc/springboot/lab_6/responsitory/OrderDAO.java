package com.thienloc.springboot.lab_6.responsitory;

import com.thienloc.springboot.lab_6.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDAO extends JpaRepository<Order, Long> {
}
