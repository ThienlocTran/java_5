package com.thienloc.springboot.lab_6.responsitory;

import com.thienloc.springboot.lab_6.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountDAO extends JpaRepository <Account, String> {
}
