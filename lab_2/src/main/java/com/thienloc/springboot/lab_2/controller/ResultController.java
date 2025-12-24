package com.thienloc.springboot.lab_2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ResultController {
    @RequestMapping("/a")
    public String m1(){
        return "a";
    }

    @RequestMapping("/b")
    public String m2(Model model){
        model.addAttribute("message", "I come from b");
        return "a";
    }

    @RequestMapping("/c")
    public String m3(RedirectAttributes params){
        params.addAttribute("message", "I come from c");
        return "redirect:/a";
    }

    @ModelAttribute("message")
    public String m4(){
        return "I come from d";
    }

    @RequestMapping("/d")
    public String showD(){
        return "a";
    }
}
