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
    //test2

    // Create a booking //todo-only this
    @PostMapping("/create")
    public ResponseEntity<Booking> createBooking(@RequestBody CreateBookingRequest request) {
        Booking booking = bookingService.createBooking(request.getUser().getId(), request.getRecommendation());
        return ResponseEntity.ok(booking);
    }

}
