package com.thienloc.springboot.lab_2.controller;

import com.thienloc.springboot.lab_2.entity.Product;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/product")
public class ProductController {
    
    @GetMapping("/form")
    public String form() {
        return "form1";
    }

    @PostMapping("/save")
    public String save(@RequestParam String name, @RequestParam Double price, Model model) {
        Product product = new Product(name, price);
        model.addAttribute("name", product.getName());
        model.addAttribute("price", product.getPrice());
        return "form1";
    }
}
