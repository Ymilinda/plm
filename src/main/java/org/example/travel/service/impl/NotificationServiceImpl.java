package org.example.travel.service.impl;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.travel.model.Booking;
import org.example.travel.model.User;
import org.example.travel.service.NotificationService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

    private final JavaMailSender mailSender;
    private final TemplateEngine templateEngine;

    @Value("${notification.email.from}")
    private String fromEmail;

    @Value("${notification.admin.email}")
    private String adminEmail;

    @Override
    public void sendBookingCreatedNotification(Booking booking) {
        Map<String, Object> templateModel = new HashMap<>();
        templateModel.put("booking", booking);
        templateModel.put("user", booking.getUser());

        sendEmail(
            booking.getUser().getEmail(),
            "Your Travel Booking Has Been Created",
            "booking-created",
            templateModel
        );
    }

    @Override
    public void sendBookingConfirmedNotification(Booking booking) {
        Map<String, Object> templateModel = new HashMap<>();
        templateModel.put("booking", booking);
        templateModel.put("user", booking.getUser());

        sendEmail(
            booking.getUser().getEmail(),
            "Your Travel Booking Is Confirmed",
            "booking-confirmed",
            templateModel
        );
    }

    @Override
    public void sendBookingCancelledNotification(Booking booking) {
        Map<String, Object> templateModel = new HashMap<>();
        templateModel.put("booking", booking);
        templateModel.put("user", booking.getUser());

        sendEmail(
            booking.getUser().getEmail(),
            "Your Travel Booking Has Been Cancelled",
            "booking-cancelled",
            templateModel
        );
    }

    @Override
    public void sendBookingFailedNotification(Booking booking) {
        Map<String, Object> templateModel = new HashMap<>();
        templateModel.put("booking", booking);
        templateModel.put("user", booking.getUser());

        sendEmail(
            booking.getUser().getEmail(),
            "Travel Booking Failed",
            "booking-failed",
            templateModel
        );

        // Also notify admin
        sendAdminNotification(
            "Booking Failed",
            String.format("Booking %d for user %s failed. Please investigate.",
                booking.getId(),
                booking.getUser().getEmail())
        );
    }

    @Override
    public void sendAdminNotification(String subject, String message) {
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
            helper.setFrom(fromEmail);
            helper.setTo(adminEmail);
            helper.setSubject(subject);
            helper.setText(message, false);
            mailSender.send(mimeMessage);
        } catch (MessagingException e) {
            log.error("Failed to send admin notification", e);
        }
    }

    @Override
    public void sendWelcomeEmail(User user) {
        Map<String, Object> templateModel = new HashMap<>();
        templateModel.put("user", user);

        sendEmail(
            user.getEmail(),
            "Welcome to Our Travel Platform",
            "welcome",
            templateModel
        );
    }

    @Override
    public void sendTravelReminder(Booking booking) {
        Map<String, Object> templateModel = new HashMap<>();
        templateModel.put("booking", booking);
        templateModel.put("user", booking.getUser());

        sendEmail(
            booking.getUser().getEmail(),
            "Your Trip Is Coming Up Soon",
            "travel-reminder",
            templateModel
        );
    }

    private void sendEmail(String to, String subject, String template, Map<String, Object> templateModel) {
        try {
            Context context = new Context();
            templateModel.forEach(context::setVariable);

            String htmlContent = templateEngine.process(template, context);

            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
            helper.setFrom(fromEmail);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(htmlContent, true);

            mailSender.send(mimeMessage);
        } catch (MessagingException e) {
            log.error("Failed to send email notification", e);
        }
    }
}