// New Controller for user-submitted product recommendations
package com.skincare.recommendations.controller;

import com.skincare.recommendations.model.UserProductRecommendation;
import com.skincare.recommendations.repository.UserProductRecommendationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import java.util.List;

@RestController
@RequestMapping("/api/user-recommendations")
public class UserRecommendationController {

    private final UserProductRecommendationRepository repository;

    @Autowired
    public UserRecommendationController(UserProductRecommendationRepository repository) {
        this.repository = repository;
    }

    // Endpoint to allow users to submit a new product recommendation
    @PostMapping("/submit")
    public ResponseEntity<UserProductRecommendation> submitRecommendation(@RequestBody UserProductRecommendation recommendation) {
        UserProductRecommendation savedRec = repository.save(recommendation);
        return ResponseEntity.ok(savedRec);
    }

    // Endpoint to retrieve all user-submitted recommendations
    @GetMapping("/all")
    public List<UserProductRecommendation> getAllUserRecommendations() {
        return repository.findAll();
    }
}
