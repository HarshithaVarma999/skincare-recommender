package com.skincare.recommendations.repository;

import com.skincare.recommendations.model.UserProductRecommendation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UserProductRecommendationRepository extends JpaRepository<UserProductRecommendation, Long> {

    @Query(value = """
        SELECT *
        FROM user_product_recommendation
        WHERE LOWER(skin_type) = LOWER(:skinType)
          AND LOWER(concerns) = LOWER(:concern)
        """, nativeQuery = true)
    List<UserProductRecommendation> findBySkinTypeAndConcerns(
            @Param("skinType") String skinType,
            @Param("concern") String concern
    );
}
