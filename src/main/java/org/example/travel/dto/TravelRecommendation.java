package org.example.travel.dto;

import lombok.Data;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Data
public class TravelRecommendation {
    private List<ItineraryDay> itinerary;
    private BigDecimal estimatedTotalCost;


    @Data
    public static class ItineraryDay {
        private List<Activity> activities;
        private Accommodation accommodation;
        private Transportation transportation;
    }

    @Data
    public static class Activity {
        private String name;
        private BigDecimal estimatedCost;
        private boolean requiresBooking;
    }

    @Data
    public static class Accommodation {
        private String name;
        private BigDecimal pricePerNight;
    }

    @Data
    public static class Transportation {
        private String from;
        private String to;
        private BigDecimal estimatedCost;
    }

}