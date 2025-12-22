package com.thienloc.springboot.lab_1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloController {

    @RequestMapping("/")
    public String sayHello(Model model){
        model.addAttribute("title", "Hello World");
        model.addAttribute("subject", "Tran Thien Loc create a project use Spring Boot MVC");
        return "hello";
    }
}
