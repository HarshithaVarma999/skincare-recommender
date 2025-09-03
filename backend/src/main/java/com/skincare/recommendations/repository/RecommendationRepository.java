package com.skincare.recommendations.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.skincare.recommendations.model.Recommendation;


public interface RecommendationRepository extends JpaRepository<Recommendation, Long> {
    List<Recommendation> findBySkinTypeAndConcernIn(String skinType, List<String> concerns);
}