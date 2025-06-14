package org.example.travel.service;

import org.example.travel.dto.TravelRecommendation;
import org.example.travel.model.Booking;
import org.example.travel.model.User;

import java.util.List;
import java.util.Optional;

public interface BookingService {
    /**
     * Create a new booking based on a selected travel recommendation
     * @param userId The user making the booking
     * @param recommendation The selected travel recommendation
     * @return The created booking
     */
    Booking createBooking(Long userId, TravelRecommendation recommendation);

    /**
     * Confirm a booking by processing all its items (flights, hotels, activities)
     * @param bookingId The ID of the booking to confirm
     * @return The confirmed booking
     */
    Booking confirmBooking(Long bookingId);

    /**
     * Cancel a booking and its associated items
     * @param bookingId The ID of the booking to cancel
     * @return The cancelled booking
     */
    Booking cancelBooking(Long bookingId);

    /**
     * Retrieve a booking by its ID
     * @param bookingId The booking ID
     * @return The booking if found
     */
    Optional<Booking> getBooking(Long bookingId);

    /**
     * Get all bookings for a user
     * @param userId The user ID
     * @return List of user's bookings
     */
    List<Booking> getUserBookings(Long userId);

    /**
     * Update booking status
     * @param bookingId The booking ID
     * @param status The new status
     * @return The updated booking
     */
    Booking updateBookingStatus(Long bookingId, Booking.BookingStatus status);
}