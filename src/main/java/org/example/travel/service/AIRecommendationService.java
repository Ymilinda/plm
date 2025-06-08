package org.example.travel.service;

import org.example.travel.model.TravelPreference;
import org.example.travel.dto.TravelRecommendation;
import java.util.List;

public interface AIRecommendationService {
    /**
     * Generate travel recommendations based on user preferences
     * @param preference The user's travel preferences
     * @return A list of travel recommendations ranked by match quality
     */
    List<TravelRecommendation> generateRecommendations(TravelPreference preference);

    /**
     * Analyze user input and extract travel preferences
     * @param userInput Natural language input from the user
     * @return Structured travel preferences
     */
    TravelPreference analyzeUserInput(String userInput);

    /**
     * Calculate confidence score for a recommendation
     * @param recommendation The travel recommendation
     * @param preference The user's preferences
     * @return A score between 0 and 1 indicating how well the recommendation matches preferences
     */
    double calculateConfidenceScore(TravelRecommendation recommendation, TravelPreference preference);
}