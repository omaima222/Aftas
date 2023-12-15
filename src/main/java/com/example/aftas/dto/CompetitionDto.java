package com.example.aftas.dto;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class CompetitionDto {
    String code;
    LocalDate date;
    LocalTime startTime;
    LocalTime endTime;
    String location;
    Integer participantsNum;
}
