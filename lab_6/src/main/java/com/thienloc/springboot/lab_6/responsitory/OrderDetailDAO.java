package com.thienloc.springboot.lab_6.responsitory;

import com.thienloc.springboot.lab_6.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailDAO extends JpaRepository<OrderDetail, Long> {
}
