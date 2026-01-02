package com.thienloc.springboot.lab_6.controller;

import com.thienloc.springboot.lab_6.entity.Account;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {
    
    private final HttpSession session;
    
    public AdminController(HttpSession session) {
        this.session = session;
    }
    
    @GetMapping("/admin")
    public String adminIndex() {
        return "redirect:/admin/dashboard";
    }
    
    @GetMapping("/admin/home/index")
    public String adminHome(Model model) {
        // This URL is excluded from interceptor, so anyone can access
        return "admin/home";
    }
    
    @GetMapping("/admin/dashboard")
    public String adminDashboard(Model model) {
        Account user = (Account) session.getAttribute("user");
        model.addAttribute("user", user);
        return "admin/dashboard";
    }
    
    @GetMapping("/admin/users")
    public String adminUsers(Model model) {
        Account user = (Account) session.getAttribute("user");
        model.addAttribute("user", user);
        return "admin/home";
    }
}