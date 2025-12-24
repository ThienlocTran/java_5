package com.thienloc.springboot.lab_2.controller;

import com.thienloc.springboot.lab_2.entity.Product;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.List;

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

    @GetMapping("/form/bai4")
    public String formBai4(Model model) {
        Product p = new Product();
        p.setName("iPhone 30");
        p.setPrice(5000.0);
        model.addAttribute("product", p);
        model.addAttribute("items", getItems());
        return "form_bai4";
    }

    @PostMapping("/save_bai4")
    public String saveBai4(@RequestParam String name, @RequestParam Double price, Model model) {
        Product product = new Product(name, price);
        model.addAttribute("product", product);
        model.addAttribute("items", getItems());
        return "form_bai4";
    }

    public List<Product> getItems() {
        return Arrays.asList(
            new Product("A", 1000000.0),
            new Product("B", 1200000.0)
        );
    }
}
