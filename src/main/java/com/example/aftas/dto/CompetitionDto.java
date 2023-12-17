package com.example.aftas.dto;

import jakarta.validation.constraints.*;
import lombok.Data;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class CompetitionDto {
    @NotEmpty
    @NotNull
    @NotBlank
    String code;

    @NotNull
    LocalDate date;

    @NotNull
    LocalTime startTime;

    @NotNull
    LocalTime endTime;

    @NotEmpty
    @NotNull
    @NotBlank
    String location;

    @NotNull
    @Positive
    Integer participantsNum;
}
