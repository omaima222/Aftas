package com.example.aftas.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class RankingDto {
    @NotNull
    @Positive
    Integer rank;

    @NotNull
    @Positive
    Integer score;

    @NotNull
    Long member_id;

    @NotNull
    Long competition_id;
}
