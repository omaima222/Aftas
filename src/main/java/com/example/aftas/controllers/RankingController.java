package com.example.aftas.controllers;

import com.example.aftas.dto.Ranking.RequestRankingDto;
import com.example.aftas.dto.Ranking.ResponseRankingDto;
import com.example.aftas.entities.Competition;
import com.example.aftas.entities.Ranking;
import com.example.aftas.services.CompetitionServiceImp;
import com.example.aftas.services.RankingServiceImp;
import com.example.aftas.services.interfaces.CompetitionService;
import com.example.aftas.services.interfaces.RankingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ranking")
@RequiredArgsConstructor
public class RankingController {
    private final RankingService rankingService;

    @GetMapping("")
    public List<ResponseRankingDto> getAll(){return this.rankingService.getAll();}

    @PostMapping("")
    public ResponseEntity<ResponseRankingDto> register(@Valid  @RequestBody RequestRankingDto requestRankingDto){
        return ResponseEntity.ok(this.rankingService.register(requestRankingDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id){
        this.rankingService.delete(id);
        return ResponseEntity.ok("Ranking deleted successfully !");
    }
    @GetMapping("/EndCompetition/{id}")
    @PreAuthorize("hasAnyRole('ROLE_MANAGER','ROLE_JURY' ) and hasAuthority('CAN_MANAGE_COMPETITON')")
    public ResponseEntity<String> endCompetition(@PathVariable Long id){
        this.rankingService.endCompetition(id);
        return ResponseEntity.ok("Scores successfully saved.");
    }

}
