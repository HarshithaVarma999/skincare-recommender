package com.skincare.recommendations.controller;

import com.skincare.recommendations.dto.RecommendationResponse;
import com.skincare.recommendations.model.UserProductRecommendation;
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

    // 🔹 Curated recs (split into multiple lines)
    skincareService.getTopRecommendations(skinType, concerns)
            .forEach(r -> {
                if (r.getRecommendations() != null) {
                    String[] recParts = r.getRecommendations().split("[;\n]");
                    for (String part : recParts) {
                        String trimmed = part.trim();
                        if (!trimmed.isEmpty()) {
                            responses.add(new RecommendationResponse(
                                    "top",
                                    r.getSkinType(),
                                    r.getConcern(),
                                    trimmed
                            ));
                        }
                    }
                }
            });

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

    /**
     * ✅ Submit a new community recommendation
     * Returns the saved entry as DTO
     */
    @PostMapping("/user-recommendations")
    public RecommendationResponse submitUserRecommendation(
            @RequestBody UserProductRecommendation recommendation) {

        var saved = skincareService.saveCommunityRecommendation(recommendation);

        return new RecommendationResponse(
                "community",
                saved.getSkinType(),
                saved.getConcerns(),
                saved.getProductType(),
                saved.getBrandName(),
                saved.getProductName()
        );
    }

    /**
     * ✅ Fetch all community recommendations
     * Returns them as DTOs
     */
    @GetMapping("/user-recommendations")
    public List<RecommendationResponse> getAllUserRecommendations() {
        var allCommunity = skincareService.getAllCommunityRecommendations();

        List<RecommendationResponse> responses = new ArrayList<>();
        allCommunity.forEach(c -> responses.add(
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
