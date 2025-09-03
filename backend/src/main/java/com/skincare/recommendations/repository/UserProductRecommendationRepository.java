package com.skincare.recommendations.repository;

import com.skincare.recommendations.model.UserProductRecommendation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserProductRecommendationRepository extends JpaRepository<UserProductRecommendation, Long> {

    // Match concerns stored as CSV using LIKE
     @Query(value = """
        SELECT *
        FROM user_product_recommendation
        WHERE skin_type = :skinType
          AND FIND_IN_SET(:concern, concerns) > 0
        """, nativeQuery = true)
    List<UserProductRecommendation> findBySkinTypeAndConcern(
            @Param("skinType") String skinType,
            @Param("concern") String concern
    );}
