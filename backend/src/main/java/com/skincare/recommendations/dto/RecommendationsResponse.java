package com.skincare.recommendations.dto;

import java.util.List;

public class RecommendationsResponse {
    private List<String> defaultRecommendations;
    private List<String> communityRecommendations;

    public RecommendationsResponse() {}

    public RecommendationsResponse(List<String> defaultRecommendations, List<String> communityRecommendations) {
        this.defaultRecommendations = defaultRecommendations;
        this.communityRecommendations = communityRecommendations;
    }

    public List<String> getDefaultRecommendations() {
        return defaultRecommendations;
    }

    public void setDefaultRecommendations(List<String> defaultRecommendations) {
        this.defaultRecommendations = defaultRecommendations;
    }

    public List<String> getCommunityRecommendations() {
        return communityRecommendations;
    }

    public void setCommunityRecommendations(List<String> communityRecommendations) {
        this.communityRecommendations = communityRecommendations;
    }
}
