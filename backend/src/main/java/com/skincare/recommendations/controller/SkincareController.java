package com.skincare.recommendations.controller;

import com.skincare.recommendations.model.Recommendation;
import com.skincare.recommendations.model.UserProductRecommendation;
import com.skincare.recommendations.repository.RecommendationRepository;
import com.skincare.recommendations.repository.UserProductRecommendationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/skincare")
@CrossOrigin(origins = "*")  // allow frontend requests (important for Netlify → Render)
public class SkincareController {

    @Autowired
    private RecommendationRepository recommendationRepository;

    @Autowired
    private UserProductRecommendationRepository userProductRecommendationRepository;

    // ✅ Fetch general recommendations
    @GetMapping("/general")
    public List<Recommendation> getGeneralRecommendations(
            @RequestParam String skinType,
            @RequestParam List<String> concerns) {

        return recommendationRepository.findBySkinTypeAndConcernIn(skinType, concerns);
    }

    // ✅ Fetch personalized recommendations from user_product_recommendation table
    @GetMapping("/recommendations")
    public List<UserProductRecommendation> getRecommendations(
            @RequestParam String skinType,
            @RequestParam List<String> concerns) {

        return concerns.stream()
                .flatMap(concern ->
                        userProductRecommendationRepository
                                .findBySkinTypeAndConcerns(skinType, concern).stream()
                )
                .collect(Collectors.toList());
    }
}
