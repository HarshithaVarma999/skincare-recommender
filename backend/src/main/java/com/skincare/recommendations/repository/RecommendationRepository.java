package com.skincare.recommendations.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.skincare.recommendations.model.Recommendation;

// For curated top recommendations
public interface RecommendationRepository extends JpaRepository<Recommendation, Long> {
    List<Recommendation> findBySkinTypeIgnoreCaseAndConcernIgnoreCase(String skinType, String concern);
}

