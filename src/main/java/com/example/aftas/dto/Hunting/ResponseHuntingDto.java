package com.example.aftas.dto.Hunting;

import com.example.aftas.dto.CompetitionDto;
import com.example.aftas.dto.Fish.ResponseFishDto;
import com.example.aftas.dto.MemberDto;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class ResponseHuntingDto {
    Long id;

    @NotNull
    @Positive
    Integer fishNum;

    @NotNull
    ResponseFishDto fish;

    @NotNull
    CompetitionDto competition;

    @NotNull
    MemberDto member;
}
