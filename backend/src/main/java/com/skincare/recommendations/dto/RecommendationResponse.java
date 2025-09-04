package com.skincare.recommendations.dto;

public class RecommendationResponse {
    private String source;        // "top" or "community"
    private String skinType;
    private String concern;       // for top recs
    private String concerns;      // for community recs
    private String recommendations; // for top recs
    private String productType;   // for community recs
    private String brandName;
    private String productName;

    // ✅ Constructors
    public RecommendationResponse() {}

    // For top recs
    public RecommendationResponse(String source, String skinType, String concern, String recommendations) {
        this.source = source;
        this.skinType = skinType;
        this.concern = concern;
        this.recommendations = recommendations;
    }

    // For community recs
    public RecommendationResponse(String source, String skinType, String concerns,
                                   String productType, String brandName, String productName) {
        this.source = source;
        this.skinType = skinType;
        this.concerns = concerns;
        this.productType = productType;
        this.brandName = brandName;
        this.productName = productName;
    }

    // ✅ Getters & Setters
    public String getSource() { return source; }
    public void setSource(String source) { this.source = source; }

    public String getSkinType() { return skinType; }
    public void setSkinType(String skinType) { this.skinType = skinType; }

    public String getConcern() { return concern; }
    public void setConcern(String concern) { this.concern = concern; }

    public String getConcerns() { return concerns; }
    public void setConcerns(String concerns) { this.concerns = concerns; }

    public String getRecommendations() { return recommendations; }
    public void setRecommendations(String recommendations) { this.recommendations = recommendations; }

    public String getProductType() { return productType; }
    public void setProductType(String productType) { this.productType = productType; }

    public String getBrandName() { return brandName; }
    public void setBrandName(String brandName) { this.brandName = brandName; }

    public String getProductName() { return productName; }
    public void setProductName(String productName) { this.productName = productName; }
}
