package com.thienloc.springboot.lab5.controller;

import com.thienloc.springboot.lab5.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ShoppingCartController {
    
    @Autowired
    private ShoppingCartService cartService;
    
    @GetMapping("/cart")
    public String viewCart(Model model) {
        model.addAttribute("items", cartService.getItems());
        model.addAttribute("count", cartService.getCount());
        model.addAttribute("amount", cartService.getAmount());
        return "/cart/view";
    }
    
    @GetMapping("/cart/add/{id}")
    public String addToCart(@PathVariable Integer id) {
        cartService.add(id);
        return "redirect:/cart";
    }
    
    @PostMapping("/cart/update")
    public String updateCart(@RequestParam Integer id, @RequestParam int qty) {
        if (qty > 0) {
            cartService.update(id, qty);
        } else {
            cartService.remove(id);
        }
        return "redirect:/cart";
    }
    
    @GetMapping("/cart/remove/{id}")
    public String removeFromCart(@PathVariable Integer id) {
        cartService.remove(id);
        return "redirect:/cart";
    }
    
    @GetMapping("/cart/clear")
    public String clearCart() {
        cartService.clear();
        return "redirect:/cart";
    }
}