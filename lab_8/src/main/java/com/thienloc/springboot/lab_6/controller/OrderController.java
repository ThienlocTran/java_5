package com.thienloc.springboot.lab_6.controller;

import com.thienloc.springboot.lab_6.entity.Account;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class OrderController {
    
    private final HttpSession session;
    
    public OrderController(HttpSession session) {
        this.session = session;
    }
    
    @GetMapping("/order")
    public String orderIndex() {
        return "redirect:/order/list";
    }
    
    @GetMapping("/order/list")
    public String orderList(Model model) {
        Account user = (Account) session.getAttribute("user");
        model.addAttribute("user", user);
        return "order/list";
    }
    
    @GetMapping("/order/detail")
    public String orderDetail(Model model) {
        Account user = (Account) session.getAttribute("user");
        model.addAttribute("user", user);
        return "order/list";
    }
}