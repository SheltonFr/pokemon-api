package com.pokemon.api.services.impl;

import com.pokemon.api.dto.PokemonDto;
import com.pokemon.api.models.Pokemon;
import com.pokemon.api.repositories.PokemonRepository;
import com.pokemon.api.services.PokemonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PokemonServiceImpl implements PokemonService {

    private PokemonRepository pokemonRepository;

    @Autowired
    public PokemonServiceImpl(PokemonRepository pokemonRepository) {
        this.pokemonRepository = pokemonRepository;
    }

    @Override
    public PokemonDto createPokemon(PokemonDto pokemonDto) {
        Pokemon pokemon = mapToEntity(pokemonDto);
        Pokemon newPokemon = pokemonRepository.save(pokemon);
        PokemonDto pokemonResponse = mapToDto(newPokemon);
        return pokemonResponse;
    }

    @Override
    public List<PokemonDto> getAllPokemon() {
        List<Pokemon> pokemons = pokemonRepository.findAll();
        return pokemons.stream().map(pok -> mapToDto(pok)).collect(Collectors.toList());
    }

    private PokemonDto mapToDto(Pokemon pokemon){
        PokemonDto pokemonDto = new PokemonDto();
        pokemonDto.setName(pokemon.getName());
        pokemonDto.setType(pokemon.getType());
        pokemonDto.setId(pokemon.getId());
        return pokemonDto;
    }

    private Pokemon mapToEntity(PokemonDto pokemonDto){
        Pokemon pokemon = new Pokemon();
        pokemon.setName(pokemonDto.getName());
        pokemon.setType(pokemonDto.getType());
        return pokemon;
    }
}
