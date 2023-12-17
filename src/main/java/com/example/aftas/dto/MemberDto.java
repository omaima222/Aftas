package com.example.aftas.dto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class MemberDto {
    @NotNull
    @NotEmpty
    @NotBlank
    String firstName;

    @NotNull
    @NotEmpty
    @NotBlank
    String lastName;

    @NotNull
    @NotEmpty
    @NotBlank
    String nationality;

    @NotNull
    LocalDate accessDate;

    @NotNull
    @NotEmpty
    @NotBlank
    String identityNumber;
}
