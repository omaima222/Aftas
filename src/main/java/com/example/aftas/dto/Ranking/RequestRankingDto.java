package com.example.aftas.dto.Ranking;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RequestRankingDto {
    Long id;

    @NotNull
    Long member_id;

    @NotNull
    Long competition_id;
}
