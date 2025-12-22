package com.thienloc.springboot.lab_1.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
@RequestMapping("/match")
public class MatchHcn {
    @Autowired
    private HttpServletRequest request;

    @GetMapping("/form")
    public String showForm(){
        return "hinhchunhat";
    }

    @PostMapping("/check")
    public String checkValidate(Model model){
       try{
           String length = request.getParameter("length");
           String width = request.getParameter("width");
           if(length == null || width == null || width.isEmpty() || length.isEmpty()){
               model.addAttribute("message", "Đừng để trống ô nhập liệu nha fen!");
               model.addAttribute("messageType", "Error!");
               return "hinhchunhat";
           }

           double lengthDouble = Double.parseDouble(length);
           double widthDouble = Double.parseDouble(width);
           if(lengthDouble <= 0 || widthDouble <= 0){
               model.addAttribute("message","Nhập sai rồi kìa ba, số bé hơn hoặc bằng 0 tính kiểu gì?");
               model.addAttribute("messageType","Error!");
            return "hinhchunhat";

           }
           else {
               model.addAttribute("area", lengthDouble * widthDouble);
               model.addAttribute("perimeter",(widthDouble + lengthDouble) *2);
           }

       }
       catch(NumberFormatException e){
           model.addAttribute("message", "Giỡn mặt hả :))), chữ sao tính?");
           model.addAttribute("messageType", "Error!");
       }
       return "hinhchunhat";
       }

}
