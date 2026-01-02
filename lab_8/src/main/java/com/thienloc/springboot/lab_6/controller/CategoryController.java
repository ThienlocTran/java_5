package com.thienloc.springboot.lab_6.controller;

import com.thienloc.springboot.lab_6.entity.Category;
import com.thienloc.springboot.lab_6.repository.CategoryDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
public class CategoryController {
    
    private final CategoryDAO categoryDAO;
    
    @Autowired
    public CategoryController(CategoryDAO categoryDAO) {
        this.categoryDAO = categoryDAO;
    }

    @RequestMapping("/category/index")
    public String index(Model model){
        Category category = new Category();
        model.addAttribute("category", category);
        List<Category> list = categoryDAO.findAll();
        model.addAttribute("list", list);
        return "category/index";
    }

    @RequestMapping("/category/edit/{id}")
    public String edit(Model model, @PathVariable("id") String id) {
        Optional<Category> optionalCategory = categoryDAO.findById(id);
        Category category = optionalCategory.orElse(new Category());
        model.addAttribute("category", category);
        List<Category> list = categoryDAO.findAll();
        model.addAttribute("list", list);
        return "category/index";
    }

    @PostMapping("/category/create")
    public String create(Category item, RedirectAttributes redirectAttributes) {
        try {
            // Kiểm tra ID đã tồn tại chưa
            if (item.getId() != null && categoryDAO.existsById(item.getId())) {
                redirectAttributes.addFlashAttribute("error", "Category ID already exists!");
                return "redirect:/category/index";
            }
            categoryDAO.save(item);
            redirectAttributes.addFlashAttribute("success", "Category created successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error creating category: " + e.getMessage());
        }
        return "redirect:/category/index";
    }
    
    @PostMapping("/category/update")
    public String update(Category item, RedirectAttributes redirectAttributes) {
        try {
            // Kiểm tra category có tồn tại không
            if (item.getId() == null || !categoryDAO.existsById(item.getId())) {
                redirectAttributes.addFlashAttribute("error", "Category not found!");
                return "redirect:/category/index";
            }
            categoryDAO.save(item);
            redirectAttributes.addFlashAttribute("success", "Category updated successfully!");
            return "redirect:/category/edit/" + item.getId();
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error updating category: " + e.getMessage());
            return "redirect:/category/index";
        }
    }
    
    @RequestMapping("/category/delete/{id}")
    public String delete(@PathVariable("id") String id, RedirectAttributes redirectAttributes) {
        try {
            categoryDAO.deleteById(id);
            redirectAttributes.addFlashAttribute("success", "Category deleted successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", 
                "Cannot delete category! It may have associated products. Please delete products first.");
        }
        return "redirect:/category/index";
    }
}
