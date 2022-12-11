package com.pokemon.api.controllers;

import com.pokemon.api.dto.PokemonDto;
import com.pokemon.api.models.Pokemon;
import com.pokemon.api.services.PokemonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/pokemon/")
public class PokemonController {

    private PokemonService pokemonService;

    @Autowired
    public PokemonController(PokemonService pokemonService) {
        this.pokemonService = pokemonService;
    }

    @GetMapping("/")
    public ResponseEntity<List<PokemonDto>> getPokemons(){
        return ResponseEntity.ok(pokemonService.getAllPokemon());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pokemon> getPokemonById(@PathVariable int id){
        return ResponseEntity.ok(new Pokemon(id, "Teste", "Teste"));
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<PokemonDto> createPokemon(@RequestBody PokemonDto pokemonDto){
        return new ResponseEntity<>(pokemonService.createPokemon(pokemonDto), HttpStatus.CREATED);
    }


    @PutMapping("/{id}/update")
    public ResponseEntity<Pokemon> updatePokemno(@RequestBody Pokemon pokemon, @PathVariable("id") int id){
        return new ResponseEntity<>(pokemon, HttpStatus.OK);
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<String> deletePokenon(@PathVariable("id") int id){
        return ResponseEntity.ok("Pokemon " + id + " deleted succesfully" );
    }
}
