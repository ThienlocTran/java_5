package com.thienloc.springboot.lab_6.service;

import jakarta.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Service("mailService")
public class MailServiceImpl implements MailService {
    final
    JavaMailSender mailSender;
    List<Mail> queue = new ArrayList<>();
    @Override
    public void push(Mail mail){
        queue.add(mail);
    }
    public MailServiceImpl(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void send(Mail mail) {
        try {
            System.out.println("=== MAIL SERVICE DEBUG ===");
            System.out.println("From: " + mail.getFrom());
            System.out.println("To: " + mail.getTo());
            System.out.println("CC: " + mail.getCc());
            System.out.println("BCC: " + mail.getBcc());
            System.out.println("Subject: " + mail.getSubject());
            System.out.println("Body: " + mail.getBody());
            
// 1. Tạo Mail
            MimeMessage message = mailSender.createMimeMessage();
// 2. Tạo đối tượng hỗ trợ ghi nội dung Mail
            MimeMessageHelper helper =
                    new MimeMessageHelper(message, true, "utf-8");
// 2.1. Ghi thông tin người gửi
            helper.setFrom(mail.getFrom());
            helper.setReplyTo(mail.getFrom());
// 2.2. Ghi thông tin người nhận
            if (mail.getTo().contains(",")) {
                // Multiple TO addresses
                String[] toAddresses = mail.getTo().split(",");
                for (int i = 0; i < toAddresses.length; i++) {
                    toAddresses[i] = toAddresses[i].trim();
                }
                helper.setTo(toAddresses);
                System.out.println("Added TO addresses: " + java.util.Arrays.toString(toAddresses));
            } else {
                // Single TO address
                helper.setTo(mail.getTo());
                System.out.println("Added single TO: " + mail.getTo());
            }
            
            if(!this.isNullOrEmpty(mail.getCc())) {
                if (mail.getCc().contains(",")) {
                    String[] ccAddresses = mail.getCc().split(",");
                    for (int i = 0; i < ccAddresses.length; i++) {
                        ccAddresses[i] = ccAddresses[i].trim();
                    }
                    helper.setCc(ccAddresses);
                } else {
                    helper.setCc(mail.getCc());
                }
                System.out.println("Added CC: " + mail.getCc());
            }
            
            if(!this.isNullOrEmpty(mail.getBcc())) {
                if (mail.getBcc().contains(",")) {
                    String[] bccAddresses = mail.getBcc().split(",");
                    for (int i = 0; i < bccAddresses.length; i++) {
                        bccAddresses[i] = bccAddresses[i].trim();
                    }
                    helper.setBcc(bccAddresses);
                } else {
                    helper.setBcc(mail.getBcc());
                }
                System.out.println("Added BCC: " + mail.getBcc());
            }
// 2.3. Ghi tiêu đề và nội dung
            helper.setSubject(mail.getSubject());
            helper.setText(mail.getBody(), true);
// 2.4. Đính kèm file
            String filenames = mail.getFilenames();
            if(!this.isNullOrEmpty(filenames)) {
                for(String filename: filenames.split("[,;]+")) {
                    File file = new File(filename.trim());
                    helper.addAttachment(file.getName(), file);
                }
            }
//3. Gửi Mail
            mailSender.send(message);
            System.out.println("✅ Mail sent successfully!");
        } catch (Exception e) {
            System.err.println("❌ Mail sending failed: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    private boolean isNullOrEmpty(String text) {
        return (text == null || text.trim().isEmpty());
    }

    @Scheduled(fixedDelay = 500)
    public void run() {
        if (!queue.isEmpty()) {
            System.out.println("📧 Processing mail queue, size: " + queue.size());
        }
        while (!queue.isEmpty()) {
            try {
                Mail mail = queue.remove(0);
                System.out.println("📤 Sending queued mail to: " + mail.getTo());
                this.send(mail);
                System.out.println("✅ Queued mail sent successfully!");
            } catch (Exception e) {
                System.err.println("❌ Failed to send queued mail: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }}

