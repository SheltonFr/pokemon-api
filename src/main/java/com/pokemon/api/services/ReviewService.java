package com.pokemon.api.services;

import com.pokemon.api.dto.ReviewDto;

import java.util.List;

public interface ReviewService {

    ReviewDto createReview(int pokemonId, ReviewDto reviewDto);
    List<ReviewDto> getReviewsByPokemon_Id(int id);
    ReviewDto findById(int id);
}
