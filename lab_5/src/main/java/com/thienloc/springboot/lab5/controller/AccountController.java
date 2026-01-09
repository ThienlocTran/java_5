package com.thienloc.springboot.lab5.controller;

import com.thienloc.springboot.lab5.service.CookieService;
import com.thienloc.springboot.lab5.service.ParamService;
import com.thienloc.springboot.lab5.service.SessionService;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@Controller
public class AccountController {
    final
    CookieService cookieService;
    final
    ParamService paramService;
    final
    SessionService sessionService;

    public AccountController(CookieService cookieService, ParamService paramService, SessionService sessionService) {
        this.cookieService = cookieService;
        this.paramService = paramService;
        this.sessionService = sessionService;
    }

    @GetMapping("/account/login")
    public String login1(){
        return "/account/login";
    }
    
    @PostMapping("/account/login")
    public String login2() {
        String un = paramService.getString("username", "");
        String pw = paramService.getString("password", "");
        boolean rm = paramService.getBoolean("remember", false);
        
        // Kiểm tra đăng nhập thành công (un="loc", pw="123")
        if ("loc".equals(un) && "123".equals(pw)) {
            // Lưu username vào session
            sessionService.set("username", un);
            
            // Xử lý ghi nhớ tài khoản
            if (rm) {
                // Nếu remember là true thì ghi nhớ tài khoản 10 ngày
                cookieService.add("user", un, 10 * 24);
            } else {
                // Ngược lại thì xóa cookie tài khoản đã ghi nhớ trước đó
                cookieService.remove("user");
            }
        }
        
        return "/account/login";
    }
    
    @GetMapping("/account/register")
    public String register1() {
        return "/account/register";
    }
    
    @PostMapping("/account/register")
    public String register2(@RequestParam("photo") MultipartFile photo) {
        String username = paramService.getString("username", "");
      //  String password = paramService.getString("password", "");
        String email = paramService.getString("email", "");
        String fullname = paramService.getString("fullname", "");
        
        // Lưu hình ảnh sử dụng ParamService.save()
        File savedFile = paramService.save(photo, "/images/users/");
        
        if (savedFile != null) {
            // Lưu thông tin đăng ký vào session (demo)
            sessionService.set("registered_username", username);
            sessionService.set("registered_email", email);
            sessionService.set("registered_fullname", fullname);
            sessionService.set("registered_photo", savedFile.getName());
        }
        
        return "/account/register";
    }
}
