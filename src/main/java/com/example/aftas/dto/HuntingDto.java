package com.example.aftas.dto;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class HuntingDto {
    @NotNull
    @Positive
    Integer fishNum;

    @NotNull
    Long fish_id;
    @NotNull
    Long competition_id;
    @NotNull
    Long member_id;
}
