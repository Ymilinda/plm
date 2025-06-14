package org.example.travel.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity
@Table(name = "booking_items")
public class BookingItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "booking_id", nullable = false)
    private Booking booking;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ItemType type;

    @Column(nullable = false)
    private String providerReference;

    @Column(nullable = false)
    private String description;

    private LocalDateTime startDateTime;

    private LocalDateTime endDateTime;

    @Column(nullable = true)
    private BigDecimal price;

    @Enumerated(EnumType.STRING)
    private ItemStatus status;

    public enum ItemType {
        FLIGHT,
        HOTEL,
        ACTIVITY
    }

    public enum ItemStatus {
        PENDING,
        CONFIRMED,
        CANCELLED
    }
}