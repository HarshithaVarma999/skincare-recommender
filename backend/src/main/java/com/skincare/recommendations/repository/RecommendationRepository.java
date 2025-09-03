package com.skincare.recommendations.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.skincare.recommendations.model.Recommendation;

public interface RecommendationRepository extends JpaRepository<Recommendation, String> {

    Recommendation findBySkinType(String skinType);

    // Pull CSV strings from the join table for the selected concerns
    @Query(value = """
        SELECT COALESCE(r.recommendations, r.recommendations_list)
        FROM recommendation_recommendations r
        WHERE r.recommendation_skin_type = :skinType
          AND r.recommendations_key IN (:concerns)
        """, nativeQuery = true)
    List<String> findCsvRecsFor(
        @Param("skinType") String skinType,
        @Param("concerns") List<String> concerns
    );
}
