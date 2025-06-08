package org.example.travel.repository;

import org.example.travel.model.TravelPreference;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TravelPreferenceRepository extends JpaRepository<TravelPreference, Long> {
    List<TravelPreference> findByUserId(Long userId);
    
    @Query("SELECT DISTINCT p.destination FROM TravelPreference p WHERE p.destination LIKE %?1%")
    List<String> findPopularDestinations(String partialName);
    
    @Query("SELECT p FROM TravelPreference p WHERE p.budget <= ?1 ORDER BY p.budget DESC")
    List<TravelPreference> findByMaxBudget(double maxBudget);
}