package com.example.aftas.controllers;

import com.example.aftas.dto.RankingDto;
import com.example.aftas.entities.Ranking;
import com.example.aftas.services.RankingService;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ranking")
public class RankingController {
    ModelMapper modelMapper = new ModelMapper();
    RankingService rankingService;

    RankingController(RankingService rankingService){this.rankingService=rankingService;}

    @GetMapping("")
    public List<Ranking> getAll(){return this.rankingService.getAll();}

    @GetMapping("/{id}")
    public Ranking find(@PathVariable Long id){return  this.rankingService.find(id);}

    @PostMapping("")
    public Ranking save(@RequestBody RankingDto rankingDto){
        Ranking ranking = modelMapper.map(rankingDto, Ranking.class);
        return this.rankingService.save(ranking, rankingDto.getCompetition_id(), rankingDto.getMember_id());
    }

    @PutMapping("/{id}")
    public Ranking update(@PathVariable Long id, @RequestBody RankingDto rankingDto){
        Ranking ranking = modelMapper.map(rankingDto, Ranking.class);
        ranking.setId(id);
        return this.rankingService.save(ranking, rankingDto.getCompetition_id(), rankingDto.getMember_id());
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        this.rankingService.delete(id);
    }
}
