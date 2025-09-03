package com.skincare.recommendations.model;

import jakarta.persistence.*;

@Entity
@Table(name = "recommendation")
public class Recommendation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "skin_type")
    private String skinType;

    @Column(name = "concern")
    private String concern;

    @Column(name = "recommendations")
    private String recommendations;

    // Getters and setters (You can use Lombok for this)
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getSkinType() { return skinType; }
    public void setSkinType(String skinType) { this.skinType = skinType; }
    public String getConcern() { return concern; }
    public void setConcern(String concern) { this.concern = concern; }
    public String getRecommendations() { return recommendations; }
    public void setRecommendations(String recommendations) { this.recommendations = recommendations; }
}