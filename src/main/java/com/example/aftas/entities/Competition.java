package com.example.aftas.entities;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Competition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String code;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
    private String location;
    private Integer participantsNum;

    @JsonIgnore
    @OneToMany(mappedBy = "competition" , fetch = FetchType.LAZY)
    private List<Ranking> rankings;

    @JsonIgnore
    @OneToMany(mappedBy = "competition" , fetch = FetchType.LAZY)
    private List<Hunting> huntings;

}
