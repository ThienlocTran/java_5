package com.thienloc.springboot.lab3.controller;

import com.thienloc.springboot.lab3.entity.Staff;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

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


    @RequestMapping("/staff/list")
    public String list(Model model){
        List<Staff> list = List.of(
                Staff.builder().id("user01@gmail.com").name("loc").level(0).build(),
                Staff.builder().id("thanhtam1@gmail.com").name("thanh").level(1).build(),
                Staff.builder().id("thuyduong@gmail.com").name("thuy").level(2).build(),
                Staff.builder().id("gon@gmail.com").name("gon").level(1).build(),
                Staff.builder().id("thaibao@gmail.com").name("thai").level(2).build(),
                Staff.builder().id("DiepLoc@gmail.com").name("diep").level(0).build()
        );
        model.addAttribute("list", list);
        return "staff-list";
    }

    @RequestMapping("staff/list/status")
    public String listStatus(Model model){
        List<Staff> list1 = List.of(
                Staff.builder().id("user01@gmail.com").name("loc").level(0).build(),
                Staff.builder().id("thanhtam@gmail.com").name("thanh").level(1).build(),
                Staff.builder().id("thuyduong@gmail.com").name("thuy").level(2).build(),
                Staff.builder().id("gon@gmail.com").name("gon").level(1).build(),
                Staff.builder().id("thaibao@gmail.com").name("thai").level(2).build(),
                Staff.builder().id("DiepLoc@gmail.com").name("diep").level(0).build()
        );
        model.addAttribute("list", list1);
        return "staff-list-status";
    }

    @RequestMapping("staff/control")
    public String listControl(Model model){
        List<Staff> list = List.of(
                Staff.builder().id("user01@gmail.com").name("loc").level(0).build(),
                Staff.builder().id("thanhtam1@gmail.com").name("thanh").level(1).build(),
                Staff.builder().id("thuyduong1@gmail.com").name("thuy").level(2).build(),
                Staff.builder().id("gon@gmail.com").name("gon").level(1).build(),
                Staff.builder().id("thaibao@gmail.com").name("thai").level(2).build(),
                Staff.builder().id("DiepLoc@gmail.com").name("diep").level(0).build()
        );
        model.addAttribute("list", list);
        return "staff-control";

    }

    @RequestMapping("/staff/create/form")
    public String createForm(Model model, @ModelAttribute("staff") Staff staff) {
        model.addAttribute("message", "Vui lòng nhập thông tin nhân viên!");
        return "staff-create";
    }
    @RequestMapping("/staff/create/save")
    public String createSave(Model model, @ModelAttribute("staff") Staff staff,
                             @RequestPart("photo_file") MultipartFile photoFile) {
// Gán tên file upload cho thuộc tính photo của bean nếu có upload file
        if(!photoFile.isEmpty()) {
            staff.setPhoto(photoFile.getName());
        }
        model.addAttribute("message", "Xin chào " + staff.getName());
        return "staff-create";
    }
}
