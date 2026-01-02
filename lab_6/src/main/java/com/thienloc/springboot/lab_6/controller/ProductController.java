package com.thienloc.springboot.lab_6.controller;

import com.thienloc.springboot.lab_6.entity.Product;
import com.thienloc.springboot.lab_6.repository.ProductDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
public class ProductController {
    
    private final ProductDAO dao;
    
    @Autowired
    public ProductController(ProductDAO dao) {
        this.dao = dao;
    }
    
    @RequestMapping("/product/sort")
    public String sort(Model model, @RequestParam("field") Optional<String> field) {
        if (field.isPresent()) {
            // Có tham số field - sắp xếp theo field
            Sort sort = Sort.by(Sort.Direction.DESC, field.get());
            model.addAttribute("field", field.get().toUpperCase());
            List<Product> items = dao.findAll(sort);
            model.addAttribute("items", items);
        } else {
            // Không có tham số - truy vấn tất cả
            List<Product> items = dao.findAll();
            model.addAttribute("items", items);
            model.addAttribute("field", "ALL");
        }
        return "product/sort";
    }

    @RequestMapping("/product/page")
    public String paginate(Model model, @RequestParam("p") Optional<Integer> p) {
        // Lấy số trang từ tham số p, mặc định là 0 nếu không có
        int pageNumber = p.orElse(0);
        
        // Tạo Pageable với page number và size = 5
        Pageable pageable = PageRequest.of(pageNumber, 5);
        Page<Product> page = dao.findAll(pageable);
        model.addAttribute("page", page);
        
        return "product/page";
    }
}
