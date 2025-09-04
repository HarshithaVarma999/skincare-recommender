package com.skincare.recommendations.controller;

import com.skincare.recommendations.dto.RecommendationResponse;
import com.skincare.recommendations.service.SkincareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/skincare")
public class SkincareController {

    private final SkincareService skincareService;

    @Autowired
    public SkincareController(SkincareService skincareService) {
        this.skincareService = skincareService;
    }

    /**
     * ✅ Combined endpoint: fetch curated + community recommendations
     * Handles multiple concerns
     */
    @GetMapping("/recommendations")
public List<RecommendationResponse> getRecommendations(
        @RequestParam String skinType,
        @RequestParam List<String> concerns) {

    List<RecommendationResponse> responses = new ArrayList<>();

    // 🔹 Curated recs
    skincareService.getTopRecommendations(skinType, concerns)
            .forEach(r -> responses.add(
                    new RecommendationResponse(
                            "top",
                            r.getSkinType(),
                            r.getConcern(),
                            r.getRecommendations()
                    )
            ));

    // 🔹 Community recs
    skincareService.getCommunityRecommendations(skinType, concerns)
            .forEach(c -> responses.add(
                    new RecommendationResponse(
                            "community",
                            c.getSkinType(),
                            c.getConcerns(),
                            c.getProductType(),
                            c.getBrandName(),
                            c.getProductName()
                    )
            ));

    return responses;
}

}
