package com.thienloc.springboot.lab5.controller;

import com.thienloc.springboot.lab5.service.CookieService;
import com.thienloc.springboot.lab5.service.ParamService;
import com.thienloc.springboot.lab5.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AccountController {
    @Autowired
    CookieService cookieService;
    @Autowired
    ParamService paramService;
    @Autowired
    SessionService sessionService;

    @GetMapping("account/login")
    public String login1(){
        return "/account/login";
    }
    @PostMapping("/account/login")
    public String login2() {
        String un = paramService.getString("username", "loc");
        String pw = paramService.getString("password", "123");
        boolean rm = paramService.getBoolean("remember", true);
        return "/account/login";
    }
}
