package com.thienloc.springboot.lab_6.service;

import com.thienloc.springboot.lab_6.entity.Account;

public interface AccountService {
    Account findById(String username);
}
