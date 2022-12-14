package com.pokemon.api.controllers;

import com.pokemon.api.dto.PokemonDto;
import com.pokemon.api.responses.PokemonResponse;
import com.pokemon.api.services.PokemonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pokemon/")
public class PokemonController {

    private PokemonService pokemonService;

    @Autowired
    public PokemonController(PokemonService pokemonService) {
        this.pokemonService = pokemonService;
    }

    @GetMapping("/")
    public ResponseEntity<PokemonResponse> getAllPokemons(
            @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize
    ){
        return ResponseEntity.ok(pokemonService.getAllPokemon(pageNo, pageSize));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PokemonDto> getPokemonById(@PathVariable int id){
        return ResponseEntity.ok(pokemonService.findPokemonById(id));
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<PokemonDto> createPokemon(@RequestBody PokemonDto pokemonDto){
        return new ResponseEntity<>(pokemonService.createPokemon(pokemonDto), HttpStatus.CREATED);
    }


    @PutMapping("/{id}/update")
    public ResponseEntity<PokemonDto> updatePokemno(@RequestBody PokemonDto pokemonDto, @PathVariable("id") int id){
        return new ResponseEntity<>(pokemonService.updatePokemon(pokemonDto, id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<String> deletePokenon(@PathVariable("id") int id){
        pokemonService.deletePokemonById(id);
        return ResponseEntity.ok("Pokemon deleted succesfully!");
    }
}
