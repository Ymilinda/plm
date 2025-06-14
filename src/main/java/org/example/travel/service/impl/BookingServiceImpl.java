package org.example.travel.service.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.travel.dto.TravelRecommendation;
import org.example.travel.model.Booking;
import org.example.travel.model.BookingItem;
import org.example.travel.model.User;
import org.example.travel.repository.BookingRepository;
import org.example.travel.repository.UserRepository;
import org.example.travel.service.BookingService;
import org.example.travel.service.NotificationService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;
    private final NotificationService notificationService;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public Booking createBooking(Long userId, TravelRecommendation recommendation) {
        User user = userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("User not found with ID: " + userId));

        Booking booking = new Booking();
        booking.setUser(user);
        booking.setBookingDate(LocalDateTime.now());
        booking.setStatus(Booking.BookingStatus.PENDING);
        booking.setTotalPrice(recommendation.getEstimatedTotalCost());

        try {
            System.out.println("Items: ");
            booking.getItems().forEach(item -> {
                System.out.println("Type: " + item.getType() + ", Price: " + item.getPrice());
            });
            Booking savedBooking = bookingRepository.save(booking);
            //notificationService.sendBookingCreatedNotification(savedBooking);
            return savedBooking;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    @Transactional
    public Booking confirmBooking(Long bookingId) {
        Booking booking = bookingRepository.findById(bookingId)
            .orElseThrow(() -> new EntityNotFoundException("Booking not found"));

        // Process all booking items
        boolean allItemsConfirmed = booking.getItems().stream()
            .map(this::processBookingItem)
            .allMatch(Boolean::booleanValue);

        if (allItemsConfirmed) {
            booking.setStatus(Booking.BookingStatus.CONFIRMED);
            notificationService.sendBookingConfirmedNotification(booking);
        } else {
            booking.setStatus(Booking.BookingStatus.CANCELLED);
            notificationService.sendBookingFailedNotification(booking);
        }

        return bookingRepository.save(booking);
    }

    @Override
    @Transactional
    public Booking cancelBooking(Long bookingId) {
        Booking booking = bookingRepository.findById(bookingId)
            .orElseThrow(() -> new EntityNotFoundException("Booking not found"));

        if (booking.getStatus() == Booking.BookingStatus.CONFIRMED) {
            // Cancel all confirmed items
            booking.getItems().stream()
                .filter(item -> item.getStatus() == BookingItem.ItemStatus.CONFIRMED)
                .forEach(item -> cancelBookingItem(item));
        }

        booking.setStatus(Booking.BookingStatus.CANCELLED);
        Booking cancelledBooking = bookingRepository.save(booking);
        notificationService.sendBookingCancelledNotification(cancelledBooking);
        return cancelledBooking;
    }

    @Override
    public Optional<Booking> getBooking(Long bookingId) {
        return bookingRepository.findById(bookingId);
    }

    @Override
    public List<Booking> getUserBookings(Long userId) {
        return bookingRepository.findByUserId(userId);
    }

    @Override
    @Transactional
    public Booking updateBookingStatus(Long bookingId, Booking.BookingStatus status) {
        Booking booking = bookingRepository.findById(bookingId)
            .orElseThrow(() -> new EntityNotFoundException("Booking not found"));
        booking.setStatus(status);
        return bookingRepository.save(booking);
    }

    private boolean processBookingItem(BookingItem item) {
        try {
            // Implementation would include actual API calls to providers
            switch (item.getType()) {
                case FLIGHT:
                    // Call flight booking API
                    break;
                case HOTEL:
                    // Call hotel booking API
                    break;
                case ACTIVITY:
                    // Call activity booking API
                    break;
            }
            item.setStatus(BookingItem.ItemStatus.CONFIRMED);
            return true;
        } catch (Exception e) {
            log.error("Failed to process booking item: {}", item, e);
            item.setStatus(BookingItem.ItemStatus.CANCELLED);
            return false;
        }
    }

    private void cancelBookingItem(BookingItem item) {
        try {
            // Implementation would include actual API calls to providers for cancellation
            item.setStatus(BookingItem.ItemStatus.CANCELLED);
        } catch (Exception e) {
            log.error("Failed to cancel booking item: {}", item, e);
        }
    }
}