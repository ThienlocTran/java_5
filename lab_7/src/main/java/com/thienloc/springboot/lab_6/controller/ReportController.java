package com.thienloc.springboot.lab_6.controller;

import com.thienloc.springboot.lab_6.repository.ProductDAO;
import com.thienloc.springboot.lab_6.repository.Report;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class ReportController {
    
    private final ProductDAO dao;
    
    @Autowired
    public ReportController(ProductDAO dao) {
        this.dao = dao;
    }
    
    @RequestMapping("/report/inventory-by-category")
    public String inventory(Model model) {
        List<Report> items = dao.getInventoryByCategory();
        model.addAttribute("items", items);
        return "report/inventory-by-category";
    }
}
