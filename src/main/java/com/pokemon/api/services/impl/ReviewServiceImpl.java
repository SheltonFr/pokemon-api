package com.pokemon.api.services.impl;

import com.pokemon.api.dto.ReviewDto;
import com.pokemon.api.exceptions.PokemonNotFoundException;
import com.pokemon.api.exceptions.ReviewNotFoundException;
import com.pokemon.api.models.Pokemon;
import com.pokemon.api.models.Review;
import com.pokemon.api.repositories.PokemonRepository;
import com.pokemon.api.repositories.ReviewRepository;
import com.pokemon.api.services.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewServiceImpl implements ReviewService {

    private ReviewRepository reviewRepository;
    private PokemonRepository pokemonRepository;

    @Autowired
    public ReviewServiceImpl(ReviewRepository reviewRepository, PokemonRepository pokemonRepository) {
        this.reviewRepository = reviewRepository;
        this.pokemonRepository = pokemonRepository;
    }

    @Override
    public ReviewDto createReview(int pokemonId, ReviewDto reviewDto) {
        Review review = mapToEntity(reviewDto);
        Pokemon pokemon = pokemonRepository.findById(pokemonId).orElseThrow(() -> new PokemonNotFoundException("Pokemon not found!"));
        review.setPokemon(pokemon);
        Review newReview = reviewRepository.save(review);
        return mapToDto(newReview);
    }

    @Override
    public List<ReviewDto> getReviewsByPokemon_Id(int id) {
        List<Review> reviews = reviewRepository.findByPokemonId(id);
        return reviews.stream().map(review -> mapToDto(review)).collect(Collectors.toList());
    }

    @Override
    public ReviewDto findById(int id) {
        Review review = reviewRepository.findById(id).orElseThrow(() -> new ReviewNotFoundException("Review Not Found!"));
        return mapToDto(review);
    }


    private ReviewDto mapToDto(Review review) {
        ReviewDto reviewDto = new ReviewDto();
        reviewDto.setId(review.getId());
        reviewDto.setStars(review.getStars());
        reviewDto.setTitle(review.getTitle());
        reviewDto.setContent(review.getContent());
        return reviewDto;
    }

    private Review mapToEntity(ReviewDto reviewDto) {
        Review review = new Review();
        review.setId(reviewDto.getId());
        review.setStars(reviewDto.getStars());
        review.setTitle(reviewDto.getTitle());
        review.setContent(reviewDto.getContent());
        return review;
    }
}
