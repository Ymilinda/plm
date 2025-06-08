package org.example.travel.service;

import org.example.travel.model.Booking;
import org.example.travel.model.User;

public interface NotificationService {
    /**
     * Send booking creation notification
     * @param booking The created booking
     */
    void sendBookingCreatedNotification(Booking booking);

    /**
     * Send booking confirmation notification
     * @param booking The confirmed booking
     */
    void sendBookingConfirmedNotification(Booking booking);

    /**
     * Send booking cancellation notification
     * @param booking The cancelled booking
     */
    void sendBookingCancelledNotification(Booking booking);

    /**
     * Send booking failure notification
     * @param booking The failed booking
     */
    void sendBookingFailedNotification(Booking booking);

    /**
     * Send notification to admin about system issues
     * @param subject The notification subject
     * @param message The detailed message
     */
    void sendAdminNotification(String subject, String message);

    /**
     * Send welcome email to new user
     * @param user The new user
     */
    void sendWelcomeEmail(User user);

    /**
     * Send travel reminder notification
     * @param booking The upcoming booking
     */
    void sendTravelReminder(Booking booking);
}