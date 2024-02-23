package com.example.aftas.dto;

import com.example.aftas.dto.Fish.ResponseFishDto;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.util.List;

@Data
public class LevelDto {
    Long id;

    @NotNull
    @Positive
    Integer code;

    @NotNull
    @Positive
    Integer points;


    @NotNull
    @NotEmpty
    @NotBlank
    String description;

    List<ResponseFishDto> fishList;
}
