package com.pokemon.api.controllers;

import com.pokemon.api.dto.ReviewDto;
import com.pokemon.api.services.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/review/")
public class ReviewController {

    private ReviewService reviewService;

    @Autowired
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping("/{pokemonId}/create")
    public ResponseEntity<ReviewDto> createReview(
            @PathVariable(name = "pokemonId") int pokemonId,
            @RequestBody ReviewDto reviewDto){
        return new ResponseEntity<>(reviewService.createReview(pokemonId, reviewDto), HttpStatus.CREATED);
    }

    @GetMapping("/{pokemonId}/{reviewId}")
    public ResponseEntity<ReviewDto> findReviewById(@PathVariable(name = "reviewId") int reviewId,
                                                    @PathVariable(name = "pokemonId") int pokemonId){
        return ResponseEntity.ok(reviewService.findReviewById(reviewId, pokemonId));
    }
    @GetMapping("/{pokemonId}/bypokemon")
    public ResponseEntity<List<ReviewDto>> findReviewsByPokemonId(@PathVariable(name = "pokemonId") int pokemonId){
        return ResponseEntity.ok(reviewService.getReviewsByPokemon_Id(pokemonId));
    }

    @PutMapping("/{pokemonId}/{reviewId}/update")
    public ResponseEntity<ReviewDto> updateReview(@PathVariable(name = "pokemonId") int pokemonId,
                                                  @PathVariable(name = "reviewId") int reviewId,
                                                  @RequestBody ReviewDto reviewDto){
      return new ResponseEntity<>(reviewService.updateReview(pokemonId, reviewId, reviewDto), HttpStatus.OK);
    }

    @DeleteMapping("/{pokemonId}/{reviewId}/delete")
    public ResponseEntity<String> deleteReview(@PathVariable(name = "pokemonId") int pokemonId,
                                                  @PathVariable(name = "reviewId") int reviewId){
        reviewService.deleteReview(pokemonId, reviewId);
        return new ResponseEntity<>("Review deleted succesfully!", HttpStatus.OK);
    }
}
