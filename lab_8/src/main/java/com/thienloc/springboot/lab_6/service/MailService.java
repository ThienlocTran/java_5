package com.thienloc.springboot.lab_6.service;

import lombok.Builder;
import lombok.Data;

public interface MailService {
    @Data
    @Builder
    class Mail{
        @Builder.Default
        private String from = "WebShop <tranthienloc21102005@gmail.com>";
        private String to, cc, bcc, subject, body, filenames;
    }
    void send(Mail mail);
    
    default void send(String to, String subject, String body) {
        Mail mail = Mail.builder().to(to).subject(subject).body(body).build();
        this.send(mail);
    }
    
    // Gửi cho nhiều người qua TO (tất cả thấy nhau)
    default void sendToMultiple(String[] toEmails, String subject, String body) {
        String toList = String.join(",", toEmails);
        Mail mail = Mail.builder().to(toList).subject(subject).body(body).build();
        this.send(mail);
    }
    
    // Gửi BCC (không thấy nhau)
    default void sendBCC(String[] bccEmails, String subject, String body) {
        Mail mail = Mail.builder()
                .to("tranthienloc21102005@gmail.com") // TO bắt buộc phải có
                .bcc(String.join(",", bccEmails))
                .subject(subject)
                .body(body)
                .build();
        this.send(mail);
    }
    
    // Gửi với TO, CC, BCC đầy đủ
    default void sendAdvanced(String to, String cc, String bcc, String subject, String body) {
        Mail mail = Mail.builder()
                .to(to)
                .cc(cc)
                .bcc(bcc)
                .subject(subject)
                .body(body)
                .build();
        this.send(mail);
    }
    
    // Gửi cho danh sách email (từng mail riêng)
    default void sendToList(String[] emails, String subject, String body) {
        for (String email : emails) {
            try {
                this.send(email.trim(), subject, body);
            } catch (Exception e) {
                // Log lỗi nhưng tiếp tục gửi cho email khác
                System.err.println("Lỗi gửi mail cho " + email + ": " + e.getMessage());
            }
        }
    }
    void push(Mail mail);
    default void push(String to, String subject, String body){
        this.push(Mail.builder().to(to).subject(subject).body(body).build());
    }

}
