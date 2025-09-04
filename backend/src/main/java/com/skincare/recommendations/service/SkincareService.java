package com.skincare.recommendations.service;

import com.skincare.recommendations.model.Recommendation;
import com.skincare.recommendations.model.UserProductRecommendation;
import com.skincare.recommendations.repository.RecommendationRepository;
import com.skincare.recommendations.repository.UserProductRecommendationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class SkincareService {

    private final RecommendationRepository recommendationRepository;
    private final UserProductRecommendationRepository userProductRecommendationRepository;

    @Autowired
    public SkincareService(
            RecommendationRepository recommendationRepository,
            UserProductRecommendationRepository userProductRecommendationRepository
    ) {
        this.recommendationRepository = recommendationRepository;
        this.userProductRecommendationRepository = userProductRecommendationRepository;
    }

    public List<Recommendation> getTopRecommendations(String skinType, List<String> concerns) {
        return recommendationRepository.findBySkinTypeIgnoreCaseAndConcernIgnoreCaseIn(skinType, concerns);
    }

    public List<UserProductRecommendation> getCommunityRecommendations(String skinType, List<String> concerns) {
        return userProductRecommendationRepository.findBySkinTypeAndConcernsIn(skinType, concerns);
    }

    public UserProductRecommendation saveCommunityRecommendation(UserProductRecommendation recommendation) {
        return userProductRecommendationRepository.save(recommendation);
    }

    public List<UserProductRecommendation> getAllCommunityRecommendations() {
        return userProductRecommendationRepository.findAll();
    }
}
