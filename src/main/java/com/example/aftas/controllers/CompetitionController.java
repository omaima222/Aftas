package com.example.aftas.controllers;

import com.example.aftas.dto.CompetitionDto;
import com.example.aftas.entities.Competition;
import com.example.aftas.services.CompetitionService;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/competition")
public class CompetitionController {
    ModelMapper modelMapper = new ModelMapper();

    CompetitionService competitionService;

    CompetitionController(CompetitionService competitionService){this.competitionService=competitionService;}

    @GetMapping("")
    public List<Competition> getAll(){return this.competitionService.getAll();}

    @GetMapping("/{id}")
    public Competition find(@PathVariable Long id){return this.competitionService.find(id);}

    @PostMapping("")
    public Competition add(@RequestBody CompetitionDto competitionDto){
        Competition competition = modelMapper.map(competitionDto, Competition.class);
        return this.competitionService.save(competition);
    }

    @PutMapping("/{id}")
    public Competition update(@PathVariable Long id, @RequestBody CompetitionDto competitionDto){
        Competition competition = modelMapper.map(competitionDto, Competition.class);
        competition.setId(id);
        return this.competitionService.save(competition);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){this.competitionService.delete(id);}


}
