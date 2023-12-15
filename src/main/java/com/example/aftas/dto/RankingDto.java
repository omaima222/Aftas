package com.example.aftas.dto;

import lombok.Data;

@Data
public class RankingDto {
    Integer rank;
    Integer score;
    Long member_id;
    Long competition_id;
}
