package com.pokemon.api.exceptions;

public class PokemonNotFoundException extends RuntimeException{

    public PokemonNotFoundException(String message){
        super(message);
    }

}
