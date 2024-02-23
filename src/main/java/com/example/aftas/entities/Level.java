package com.example.aftas.entities;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Level {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer code;
    private Integer points;
    private String description;

    @OneToMany(mappedBy = "level" , fetch = FetchType.LAZY)
    private List<Fish> fishes;

}
