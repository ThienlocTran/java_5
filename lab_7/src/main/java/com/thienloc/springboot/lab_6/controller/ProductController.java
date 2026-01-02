package com.thienloc.springboot.lab_6.controller;

import com.thienloc.springboot.lab_6.service.SessionService;
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
    private final SessionService sessionService;
    
    @Autowired
    public ProductController(ProductDAO dao, SessionService sessionService) {
        this.dao = dao;
        this.sessionService = sessionService;
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

    @RequestMapping("/product/search")
    public String search(Model model,

                         @RequestParam("min") Optional<Double> min,@RequestParam("max") Optional<Double> max) {
        double minPrice = min.orElse(Double.MIN_VALUE);
        double maxPrice = max.orElse(Double.MAX_VALUE);
        List<Product> items = dao.findByPriceBetween(minPrice, maxPrice);
        model.addAttribute("items", items);
        return "product/search";
    }

    @RequestMapping("/product/search-and-page")
    public String searchAndPage(Model model,
                                @RequestParam("keywords") Optional<String> kw,
                                @RequestParam("p") Optional<Integer> p) {
        String kwords = kw.orElse(sessionService.get("keywords", ""));
        sessionService.set("keywords", kwords);
        
        Pageable pageable = PageRequest.of(p.orElse(0), 5);
        Page<Product> page = dao.findByKeywords("%" + kwords + "%", pageable);
        model.addAttribute("page", page);
        return "product/search-and-page";
    }
}
