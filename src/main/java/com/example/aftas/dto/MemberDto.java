package com.example.aftas.dto;
import com.example.aftas.enums.IdentityDocumentation;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.util.Date;

@Data
public class MemberDto {
    Long id;

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
    String email;

    @NotNull
    @NotEmpty
    @NotBlank
    String username;

    @NotNull
    @NotEmpty
    @NotBlank
    String password;

    @NotNull
    @NotEmpty
    @NotBlank
    String nationality;

    Date accessDate;

    @NotNull
    @NotEmpty
    @NotBlank
    String identityNumber;

    @NotNull
    @NotEmpty
    @NotBlank
    String role;

    @NotNull
    IdentityDocumentation identityDocument;

}
