package org.example.travel.dto;

import lombok.Data;
import org.example.travel.model.User;

@Data
public class CreateBookingRequest {
    private User user;
    private TravelRecommendation recommendation;
}
