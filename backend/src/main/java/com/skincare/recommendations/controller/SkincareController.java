package com.skincare.recommendations.controller;

import com.skincare.recommendations.repository.RecommendationRepository;
import com.skincare.recommendations.repository.UserProductRecommendationRepository;
import com.skincare.recommendations.model.Recommendation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;
import java.util.Arrays;

@RestController
@RequestMapping("/api/skincare")
public class SkincareController {

    private final RecommendationRepository recommendationRepository;
    private final UserProductRecommendationRepository userRepo;

    @Autowired
    public SkincareController(RecommendationRepository recommendationRepository,
                              UserProductRecommendationRepository userRepo) {
        this.recommendationRepository = recommendationRepository;
        this.userRepo = userRepo;
    }

    @GetMapping("/recommendations")
    public Map<String, List<String>> getRecommendations(
            @RequestParam String skinType,
            @RequestParam List<String> concerns) {

        // 1) TOP (default) recommendations from the new recommendation table
        List<Recommendation> recs = recommendationRepository.findBySkinTypeAndConcernIn(skinType, concerns);
        List<String> topRecs = recs.stream()
                .map(Recommendation::getRecommendations)
                .filter(Objects::nonNull)
                .flatMap(csv -> Arrays.stream(csv.split(",")))
                .map(String::trim)
                .filter(s -> !s.isBlank())
                .distinct()
                .collect(Collectors.toList());

        // 2) Community recommendations from user submissions (using a new PostgreSQL-compatible query)
        List<String> communityRecs = concerns.stream()
                .flatMap(concern ->
                        userRepo.findBySkinTypeAndConcern(skinType, concern).stream()
                                .map(u -> u.getBrandName() + " " + u.getProductName()))
                .distinct()
                .collect(Collectors.toList());

        Map<String, List<String>> response = new HashMap<>();
        response.put("topRecommendations", topRecs);
        response.put("communityRecommendations", communityRecs);
        return response;
    }
}