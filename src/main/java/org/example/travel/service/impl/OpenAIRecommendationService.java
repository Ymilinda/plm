package org.example.travel.service.impl;


import com.theokanning.openai.completion.CompletionRequest;
import com.theokanning.openai.service.OpenAiService;
import lombok.extern.slf4j.Slf4j;
import org.example.travel.dto.TravelRecommendation;
import org.example.travel.model.TravelPreference;
import org.example.travel.service.AIRecommendationService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class OpenAIRecommendationService implements AIRecommendationService {

    private final OpenAiService openAiService;

    public OpenAIRecommendationService(@Value("${openai.api.key}") String apiKey) {
        this.openAiService = new OpenAiService(apiKey);
    }

    @Override
    public List<TravelRecommendation> generateRecommendations(TravelPreference preference) {
        try {
            String prompt = buildRecommendationPrompt(preference);
            CompletionRequest request = CompletionRequest.builder()
                    .model("gpt-3.5-turbo")
                    .prompt(prompt)
                    .maxTokens(1000)
                    .temperature(0.7)
                    .build();

            String response = openAiService.createCompletion(request)
                    .getChoices().get(0).getText();

            return parseRecommendations(response, preference);
        } catch (Exception e) {
            log.error("Error generating travel recommendations", e);
            throw new RuntimeException("Failed to generate travel recommendations", e);
        }
    }

    @Override
    public TravelPreference analyzeUserInput(String userInput) {
        try {
            String prompt = buildPreferenceAnalysisPrompt(userInput);
            CompletionRequest request = CompletionRequest.builder()
                    .model("gpt-3.5-turbo")
                    .prompt(prompt)
                    .maxTokens(500)
                    .temperature(0.3)
                    .build();

            String response = openAiService.createCompletion(request)
                    .getChoices().get(0).getText();

            return parsePreferences(response);
        } catch (Exception e) {
            log.error("Error analyzing user input", e);
            throw new RuntimeException("Failed to analyze user input", e);
        }
    }

    @Override
    public double calculateConfidenceScore(TravelRecommendation recommendation, TravelPreference preference) {
        // Implement scoring logic based on how well the recommendation matches preferences
        double score = 0.0;
        
        // Budget match (30% weight)
        double budgetDiff = Math.abs(recommendation.getEstimatedTotalCost()
                .subtract(preference.getBudget())
                .doubleValue() / preference.getBudget().doubleValue());
        score += (1 - budgetDiff) * 0.3;

        // Duration match (20% weight)
        if (recommendation.getItinerary().size() == 
            preference.getEndDate().toEpochDay() - preference.getStartDate().toEpochDay()) {
            score += 0.2;
        }

        // Activities match (30% weight)
        double activityMatchScore = calculateActivityMatchScore(recommendation, preference);
        score += activityMatchScore * 0.3;

        // Location match (20% weight)
//        if (recommendation.getDestination().equalsIgnoreCase(preference.getDestination())) {
//            score += 0.2;
//        }

        return Math.min(1.0, Math.max(0.0, score));
    }

    private String buildRecommendationPrompt(TravelPreference preference) {
        return String.format(
            "Generate a detailed travel itinerary for the following preferences:\\n" +
            "Destination: %s\\n" +
            "Start Date: %s\\n" +
            "End Date: %s\\n" +
            "Budget: %s\\n" +
            "Preferred Activities: %s\\n" +
            "Additional Notes: %s",
            preference.getDestination(),
            preference.getStartDate(),
            preference.getEndDate(),
            preference.getBudget(),
            String.join(", ", preference.getPreferredActivities()),
            preference.getAdditionalNotes()
        );
    }

    private String buildPreferenceAnalysisPrompt(String userInput) {
        return "Extract travel preferences from the following user input:\\n" + userInput;
    }

    private List<TravelRecommendation> parseRecommendations(String aiResponse, TravelPreference preference) {
        // Implementation would parse AI response into structured recommendations
        // This is a simplified version
        List<TravelRecommendation> recommendations = new ArrayList<>();
        // TODO: Implement proper parsing logic
        return recommendations;
    }

    private TravelPreference parsePreferences(String aiResponse) {
        // Implementation would parse AI response into structured preferences
        // This is a simplified version
        TravelPreference preference = new TravelPreference();
        // TODO: Implement proper parsing logic
        return preference;
    }

    private double calculateActivityMatchScore(TravelRecommendation recommendation, TravelPreference preference) {
        // TODO: Implement detailed activity matching logic
        return 0.5; // Placeholder implementation
    }
}