package org.example.travel.repository;

import org.example.travel.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findByUserId(Long userId);
    
    @Query("SELECT b FROM Booking b WHERE b.status = 'CONFIRMED' AND b.user.id = ?1 ORDER BY b.bookingDate DESC")
    List<Booking> findConfirmedBookingsByUser(Long userId);
    
    @Query("SELECT b FROM Booking b JOIN b.items i WHERE i.startDateTime BETWEEN ?1 AND ?2")
    List<Booking> findUpcomingBookings(LocalDateTime start, LocalDateTime end);
}