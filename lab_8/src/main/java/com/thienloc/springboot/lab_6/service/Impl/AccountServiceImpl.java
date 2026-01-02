package com.thienloc.springboot.lab_6.service.Impl;

import com.thienloc.springboot.lab_6.entity.Account;
import com.thienloc.springboot.lab_6.repository.AccountDAO;
import com.thienloc.springboot.lab_6.service.AccountService;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {
    
    private final AccountDAO accountDAO;
    
    public AccountServiceImpl(AccountDAO accountDAO) {
        this.accountDAO = accountDAO;
    }
    
    @Override
    public Account findById(String username) {
        return accountDAO.findById(username).orElse(null);
    }
}
