package org.example.travel.controller;

import lombok.RequiredArgsConstructor;
import org.example.travel.dto.CreateBookingRequest;
import org.example.travel.dto.TravelRecommendation;
import org.example.travel.model.Booking;
import org.example.travel.model.User;
import org.example.travel.service.BookingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/bookings")
@RequiredArgsConstructor
public class BookingController {
    //test

    private final BookingService bookingService;

    // Create a booking
    @PostMapping("/create")
    public ResponseEntity<Booking> createBooking(@RequestBody CreateBookingRequest request) {
        Booking booking = bookingService.createBooking(request.getUser(), request.getRecommendation());
        return ResponseEntity.ok(booking);
    }

    // Confirm a booking
    @PostMapping("/{bookingId}/confirm")
    public ResponseEntity<Booking> confirmBooking(@PathVariable Long bookingId) {
        Booking booking = bookingService.confirmBooking(bookingId);
        return ResponseEntity.ok(booking);
    }

    // Cancel a booking
    @PostMapping("/{bookingId}/cancel")
    public ResponseEntity<Booking> cancelBooking(@PathVariable Long bookingId) {
        Booking booking = bookingService.cancelBooking(bookingId);
        return ResponseEntity.ok(booking);
    }

    // Get a specific booking
    @GetMapping("/{bookingId}")
    public ResponseEntity<Booking> getBooking(@PathVariable Long bookingId) {
        Optional<Booking> booking = bookingService.getBooking(bookingId);
        return booking.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    // Get all bookings for a user
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Booking>> getUserBookings(@PathVariable Long userId) {
        List<Booking> bookings = bookingService.getUserBookings(userId);
        return ResponseEntity.ok(bookings);
    }
}
