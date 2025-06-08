package com.nic.emailnotification.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import jakarta.mail.internet.MimeMessage;

@Service
public class EmailService {
	
	 @Autowired
	    private JavaMailSender mailSender;

	    @Autowired
	    private TemplateEngine templateEngine;

	    public void sendWelcomeEmail(String to, String name) {
	        Context context = new Context();
	        context.setVariable("name", name);
	        System.out.println(context);

	        String processHtml = templateEngine.process("welcome-email", context);

	        MimeMessage mimeMessage = mailSender.createMimeMessage();
	        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");

	        try {
	            helper.setText(processHtml, true); // true indicates HTML
	            helper.setTo(to);
	            helper.setSubject("Welcome to Our Service!");
	            helper.setFrom("kumarrahul21269@gmail.com");
	            mailSender.send(mimeMessage);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }

}
