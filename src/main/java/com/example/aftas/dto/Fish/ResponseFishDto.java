package com.example.aftas.dto.Fish;

import com.example.aftas.dto.LevelDto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class ResponseFishDto {
    Long id;

    @NotEmpty
    @NotNull
    @NotBlank
    String name;

    @NotNull
    @Positive
    Integer avgWeight;

}
