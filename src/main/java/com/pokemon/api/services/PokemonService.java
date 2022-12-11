package com.pokemon.api.services;

import com.pokemon.api.dto.PokemonDto;
import com.pokemon.api.models.Pokemon;

import java.util.List;

public interface PokemonService {
    PokemonDto createPokemon(PokemonDto pokemonDto);
    List<PokemonDto> getAllPokemon();
    PokemonDto findPokemonById(int id);

}
