package com.example.aftas.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Fish {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Integer avgWeight;

    @JsonIgnore
    @OneToMany(mappedBy = "fish" , fetch = FetchType.LAZY)
    private List<Hunting> huntings;

    @ManyToOne
    @JoinColumn(name = "level_id")
    private Level level;
}
