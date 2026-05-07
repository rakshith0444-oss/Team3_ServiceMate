package com.servicemate.servicemate.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

/**
 * Sends HTML OTP emails via Gmail SMTP (port 587 / STARTTLS).
 * Credentials are injected from application.properties via env vars
 * MAIL_USERNAME and MAIL_PASSWORD.
 */
@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String fromAddress;

    public void sendOtpEmail(String toEmail, String otpCode) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            helper.setFrom(fromAddress);
            helper.setTo(toEmail);
            helper.setSubject("Your ServiceMate OTP — " + otpCode);

            String html = """
                    <div style="font-family:sans-serif;max-width:480px;margin:0 auto;background:#0F1320;
                                border-radius:16px;padding:32px;color:#fff;">
                      <div style="font-size:22px;font-weight:800;letter-spacing:-0.5px;margin-bottom:8px;">
                        Service<span style="color:#4F7EFF;">Mate</span>
                      </div>
                      <hr style="border:none;border-top:1px solid rgba(255,255,255,0.1);margin:16px 0;">
                      <p style="font-size:15px;color:rgba(255,255,255,0.7);">
                        Here is your one-time login code:
                      </p>
                      <div style="text-align:center;margin:24px 0;">
                        <span style="font-size:48px;font-weight:800;letter-spacing:12px;
                                     color:#4F7EFF;">%s</span>
                      </div>
                      <p style="font-size:13px;color:rgba(255,255,255,0.4);text-align:center;">
                        This code expires in <strong>5 minutes</strong>. Do not share it with anyone.
                      </p>
                      <hr style="border:none;border-top:1px solid rgba(255,255,255,0.08);margin:24px 0 16px;">
                      <p style="font-size:11px;color:rgba(255,255,255,0.3);text-align:center;">
                        If you did not request this, please ignore this email.
                      </p>
                    </div>
                    """.formatted(otpCode);

            helper.setText(html, true);
            mailSender.send(message);

        } catch (MessagingException e) {
            throw new RuntimeException("Failed to send OTP email to " + toEmail, e);
        }
    }
}
