package com.pokemon.api.services;

import com.pokemon.api.dto.PokemonDto;
import com.pokemon.api.responses.PokemonResponse;

public interface PokemonService {
    PokemonDto createPokemon(PokemonDto pokemonDto);
    PokemonResponse getAllPokemon(int pageNo, int pageSize);
    PokemonDto findPokemonById(int id);
    PokemonDto updatePokemon(PokemonDto pokemonDto, int id);
    void deletePokemonById(int id);

}
