package com.example.aftas.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AuthDto {
    @NotNull
    @NotEmpty
    @NotBlank
    public String email;

    @NotNull
    @NotEmpty
    @NotBlank
    public String password;
}
