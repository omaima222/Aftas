package com.example.aftas.dto;

import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.Data;

@Data
public class LevelDto {
    Integer code;
    Integer points;
    String description;
}
