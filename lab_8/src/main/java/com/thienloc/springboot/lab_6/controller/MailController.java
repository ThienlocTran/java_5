package com.thienloc.springboot.lab_6.controller;

import com.thienloc.springboot.lab_6.service.MailService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MailController {
    private final MailService mailService;

    public MailController(MailService mailService) {
        this.mailService = mailService;
    }

    @ResponseBody
    @RequestMapping("/mail/send")
    public String send() {
        try {
            mailService.push("vinhnguyen125812@gmail.com", "Test Subject", "Test Body from WebShop");
            return "✅ Mail đã được xếp vào hàng đợi!";
        } catch (Exception e) {
            return "❌ Lỗi xếp mail vào hàng đợi: " + e.getMessage();
        }
    }
    
    @RequestMapping("/mail/test")
    public String testForm() {
        return "mail/test";
    }
    
    @RequestMapping(value = "/mail/test", params = {"to", "subject", "body"})
    public String testSend(Model model,
                          @RequestParam("to") String to,
                          @RequestParam("subject") String subject,
                          @RequestParam("body") String body) {
        try {
            mailService.push(to, subject, body);
            model.addAttribute("result", "✅ Mail đã được xếp vào hàng đợi: " + to);
            model.addAttribute("success", true);
        } catch (Exception e) {
            model.addAttribute("result", "❌ Lỗi xếp mail vào hàng đợi: " + e.getMessage());
            model.addAttribute("success", false);
        }
        return "mail/test";
    }
    
    @RequestMapping("/mail/test-multiple")
    public String testMultiple(Model model,
                              @RequestParam("emails") String emails,
                              @RequestParam("method") String method,
                              @RequestParam("subject") String subject,
                              @RequestParam("body") String body) {
        
        // Debug log
        System.out.println("=== MULTIPLE EMAIL DEBUG ===");
        System.out.println("Emails: " + emails);
        System.out.println("Method: " + method);
        System.out.println("Subject: " + subject);
        System.out.println("Body: " + body);
        
        try {
            // Parse emails
            String[] emailArray = emails.split("[,;\\s]+");
            System.out.println("Parsed " + emailArray.length + " emails:");
            for (int i = 0; i < emailArray.length; i++) {
                emailArray[i] = emailArray[i].trim();
                System.out.println("  " + (i+1) + ": " + emailArray[i]);
            }
            
            // Send based on method
            switch (method) {
                case "to":
                    System.out.println("Sending via TO method...");
                    mailService.sendToMultiple(emailArray, subject, body);
                    model.addAttribute("result", "✅ Mail đã gửi TO cho " + emailArray.length + " người: " + String.join(", ", emailArray));
                    break;
                case "bcc":
                    System.out.println("Sending via BCC method...");
                    mailService.sendBCC(emailArray, subject, body);
                    model.addAttribute("result", "✅ Mail đã gửi BCC cho " + emailArray.length + " người");
                    break;
                case "individual":
                    System.out.println("Sending via Individual method...");
                    mailService.sendToList(emailArray, subject, body);
                    model.addAttribute("result", "✅ Đã gửi " + emailArray.length + " mail riêng lẻ");
                    break;
                default:
                    throw new IllegalArgumentException("Method không hợp lệ: " + method);
            }
            model.addAttribute("success", true);
            System.out.println("✅ Multiple email sent successfully!");
            
        } catch (Exception e) {
            System.err.println("❌ Multiple email failed: " + e.getMessage());
            e.printStackTrace();
            model.addAttribute("result", "❌ Lỗi gửi mail: " + e.getMessage());
            model.addAttribute("success", false);
        }
        return "mail/test";
    }
}

