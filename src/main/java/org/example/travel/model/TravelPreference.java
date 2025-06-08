package org.example.travel.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
@Table(name = "travel_preferences")
public class TravelPreference {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false)
    private String destination;

    @Column(nullable = false)
    private LocalDate startDate;

    @Column(nullable = false)
    private LocalDate endDate;

    @Column(nullable = false)
    private BigDecimal budget;

    @ElementCollection
    @CollectionTable(name = "preferred_activities")
    private Set<String> preferredActivities;

    @Column(length = 1000)
    private String additionalNotes;

    @Enumerated(EnumType.STRING)
    private AccommodationType preferredAccommodationType;

    public enum AccommodationType {
        HOTEL,
        RESORT,
        APARTMENT,
        HOSTEL,
        VILLA
    }
}