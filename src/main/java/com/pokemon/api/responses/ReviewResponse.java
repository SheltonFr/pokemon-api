package com.pokemon.api.responses;

import com.pokemon.api.dto.ReviewDto;
import lombok.Data;

import java.util.List;

@Data
public class ReviewResponse {
    private List<ReviewDto> content;
    private int pageNo;
    private int pageSize;
    private int totalElements;
    private int totalPages;
    private boolean isLast;
}
