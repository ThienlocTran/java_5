package com.thienloc.springboot.lab3.controller;

import com.thienloc.springboot.lab3.entity.Staff;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class StaffController {
    @RequestMapping("/staff/detail")
    public String detail(Model model){
        Staff staff = Staff.builder()
                .id("loc@gmail.com")
                .name("Thien Loc")
                .level(2)
                .build();
        model.addAttribute("staff", staff);
        return "staff-detail";
    }
}
