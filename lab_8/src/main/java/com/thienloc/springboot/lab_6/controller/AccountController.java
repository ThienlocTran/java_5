package com.thienloc.springboot.lab_6.controller;

import com.thienloc.springboot.lab_6.entity.Account;
import com.thienloc.springboot.lab_6.service.AccountService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AccountController {
    
    private final AccountService accountService;
    private final HttpSession session;
    
    public AccountController(AccountService accountService, HttpSession session) {
        this.accountService = accountService;
        this.session = session;
    }
    
    @GetMapping("/account")
    public String accountIndex() {
        return "redirect:/account/edit-profile";
    }
    
    @GetMapping("/account/edit-profile")
    public String editProfileForm(Model model) {
        Account user = (Account) session.getAttribute("user");
        model.addAttribute("account", user);
        return "account/edit-profile";
    }
    
    @PostMapping("/account/edit-profile")
    public String editProfileProcess(Model model,
                                   @RequestParam("fullname") String fullname,
                                   @RequestParam("email") String email) {
        Account user = (Account) session.getAttribute("user");
        user.setFullname(fullname);
        user.setEmail(email);
        // Save to database here if needed
        session.setAttribute("user", user);
        model.addAttribute("message", "Profile updated successfully!");
        model.addAttribute("account", user);
        return "account/edit-profile";
    }
    
    @GetMapping("/account/change-password")
    public String changePasswordForm() {
        return "account/change-password";
    }
    
    @PostMapping("/account/change-password")
    public String changePasswordProcess(Model model,
                                      @RequestParam("currentPassword") String currentPassword,
                                      @RequestParam("newPassword") String newPassword,
                                      @RequestParam("confirmPassword") String confirmPassword) {
        Account user = (Account) session.getAttribute("user");
        
        if (!user.getPassword().equals(currentPassword)) {
            model.addAttribute("message", "Current password is incorrect!");
        } else if (!newPassword.equals(confirmPassword)) {
            model.addAttribute("message", "New passwords do not match!");
        } else {
            user.setPassword(newPassword);
            // Save to database here if needed
            session.setAttribute("user", user);
            model.addAttribute("message", "Password changed successfully!");
        }
        
        return "account/change-password";
    }
}