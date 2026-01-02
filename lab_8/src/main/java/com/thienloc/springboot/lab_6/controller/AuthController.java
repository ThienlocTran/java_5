package com.thienloc.springboot.lab_6.controller;

import com.thienloc.springboot.lab_6.entity.Account;
import com.thienloc.springboot.lab_6.service.AccountService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthController {
    final
    AccountService accountService;
    final
    HttpSession session;

    public AuthController(AccountService accountService, HttpSession session) {
        this.accountService = accountService;
        this.session = session;
    }

    @GetMapping("/auth/login")
    public String loginForm(Model model) {
        return "/auth/login";
    }
    @PostMapping("/auth/login")
    public String loginProcess(Model model,
                               @RequestParam("username") String username,
                               @RequestParam("password") String password) {
// Bổ sung code đăng nhập
        Account user = accountService.findById(username);
        if(user == null) {
            model.addAttribute("message", "Invalid username!");
        } else if(!user.getPassword().equals(password)) {
            model.addAttribute("message", "Invalid password!");
        } else if(!user.getActivated()) {
            model.addAttribute("message", "Account is not activated!");
        } else {
            session.setAttribute("user", user);
            String securityUri = (String)session.getAttribute("securityUri");
            if(securityUri != null) {
                session.removeAttribute("securityUri"); // Clear the security URI
                return "redirect:" + securityUri;
            }
            return "redirect:/category/index"; // Redirect to home page after successful login
        }

        return "/auth/login";
    }
    
    @GetMapping("/auth/logout")
    public String logout() {
        session.removeAttribute("user");
        return "redirect:/auth/login";
    }
}
