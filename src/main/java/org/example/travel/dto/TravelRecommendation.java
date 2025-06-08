package org.example.travel.dto;

import lombok.Data;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Data
public class TravelRecommendation {
    private String destination;
    private List<ItineraryDay> itinerary;
    private BigDecimal estimatedTotalCost;
    private Map<String, BigDecimal> costBreakdown;
    private double confidenceScore;
    private List<String> highlights;
    private WeatherForecast weatherForecast;
    private List<String> travelTips;

    @Data
    public static class ItineraryDay {
        private int day;
        private List<Activity> activities;
        private Accommodation accommodation;
        private Transportation transportation;
    }

    @Data
    public static class Activity {
        private String name;
        private String description;
        private BigDecimal estimatedCost;
        private String duration;
        private String location;
        private boolean requiresBooking;
    }

    @Data
    public static class Accommodation {
        private String name;
        private String type;
        private BigDecimal pricePerNight;
        private String location;
        private double rating;
        private List<String> amenities;
    }

    @Data
    public static class Transportation {
        private String type;
        private String from;
        private String to;
        private BigDecimal estimatedCost;
        private String duration;
    }

    @Data
    public static class WeatherForecast {
        private double temperature;
        private String conditions;
        private int precipitationChance;
    }
}