package com.pokemon.api.services.impl;

import com.pokemon.api.dto.PokemonDto;
import com.pokemon.api.responses.PokemonResponse;
import com.pokemon.api.exceptions.PokemonNotFoundException;
import com.pokemon.api.models.Pokemon;
import com.pokemon.api.repositories.PokemonRepository;
import com.pokemon.api.services.PokemonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
    public PokemonResponse getAllPokemon(int pageNo, int pageSize) {
        PageRequest pageable = PageRequest.of(pageNo, pageSize);
        Page<Pokemon> pokemons = pokemonRepository.findAll(pageable);
        List<Pokemon> listOfPokemons = pokemons.getContent();
        List<PokemonDto> content = listOfPokemons.stream().map(pok -> mapToDto(pok)).collect(Collectors.toList());

        PokemonResponse response = new PokemonResponse();

        response.setContent(content);
        response.setPageNo(pokemons.getNumber());
        response.setPageSize(pokemons.getSize());
        response.setTotalElements(pokemons.getTotalElements());
        response.setTotalPages(pokemons.getTotalPages());
        response.setLast(pokemons.isLast());

        return response;
    }

    @Override
    public PokemonDto findPokemonById(int id) {
        Pokemon pokemon = pokemonRepository.findById(id).orElseThrow(() -> new PokemonNotFoundException("Pokemon not found!"));
        return mapToDto(pokemon);
    }

    @Override
    public PokemonDto updatePokemon(PokemonDto pokemonDto, int id) {
        Pokemon pokemon = pokemonRepository.findById(id).orElseThrow(() -> new PokemonNotFoundException("Pokemon not found!"));
        pokemon.setName(pokemonDto.getName());
        pokemon.setType(pokemon.getType());
        Pokemon updatedPokemon = pokemonRepository.save(pokemon);
        return mapToDto(updatedPokemon);
    }

    @Override
    public void deletePokemonById(int id) {
        Pokemon pokemon = pokemonRepository.findById(id).orElseThrow(() -> new PokemonNotFoundException("Pokemon not found"));
        pokemonRepository.delete(pokemon);
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
