package com.thienloc.springboot.lab_1.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/login")
public class AuthController {

    @Autowired
    private HttpServletRequest request;

    @GetMapping("/form")
    public String showFormLogin(){
        return "form";
    }

    @PostMapping("check")
    public String checkLogin(
            // Dùng kiểu này nếu ko @Autowired, gọi trực tiếp
//            @RequestParam("username") String username,
//            @RequestParam("password") String password,
            Model model
    ){
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        if("loc".equals(username) && "loc".equals(password)){
            model.addAttribute("message","login successful");
            model.addAttribute("messageType","success");

        }
        else{
            model.addAttribute("message","Kiem tra lại User và Password kìa");
            model.addAttribute("messageType","Error!");

        }
        return "form";
    }



}
