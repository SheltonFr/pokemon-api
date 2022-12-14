package com.pokemon.api.responses;

import com.pokemon.api.dto.PokemonDto;
import lombok.Data;

import java.util.List;

@Data
public class PokemonResponse {
    private List<PokemonDto> content;
    private int pageNo;
    private int pageSize;
    private long totalElements;
    private int totalPages;
    private boolean last;
}
