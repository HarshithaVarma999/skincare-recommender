package com.skincare.recommendations.repository;

import com.skincare.recommendations.model.Recommendation;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface RecommendationRepository extends JpaRepository<Recommendation, Long> {

    // Handles multiple concerns at once
    List<Recommendation> findBySkinTypeIgnoreCaseAndConcernIgnoreCaseIn(
            String skinType,
            List<String> concerns
    );
}
