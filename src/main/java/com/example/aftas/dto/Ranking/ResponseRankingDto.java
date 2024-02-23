package com.example.aftas.dto.Ranking;

import com.example.aftas.dto.CompetitionDto;
import com.example.aftas.dto.MemberDto;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ResponseRankingDto {
    @NotNull
    Integer rank;

    @NotNull
    Integer score;

    @NotNull
    MemberDto member;

    @NotNull
    CompetitionDto competition;

}
