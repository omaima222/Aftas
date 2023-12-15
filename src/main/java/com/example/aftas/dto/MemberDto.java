package com.example.aftas.dto;
import lombok.Data;

import java.time.LocalDate;

@Data
public class MemberDto {
    String firstName;
    String lastName;
    String nationality;
    LocalDate accessDate;
    String identityNumber;
}
