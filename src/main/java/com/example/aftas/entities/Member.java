package com.example.aftas.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    private String nationality;
    private LocalDate accessDate;
    private String identityNumber;
//    private        identityDocument

    @JsonIgnore
    @OneToMany(mappedBy = "member" ,fetch = FetchType.LAZY)
    private List<Ranking> rankings;

    @JsonIgnore
    @OneToMany(mappedBy = "member" ,fetch = FetchType.LAZY)
    private List<Hunting> huntings;
}
