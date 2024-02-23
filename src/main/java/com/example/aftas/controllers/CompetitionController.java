package com.example.aftas.controllers;

import com.example.aftas.dto.CompetitionDto;
import com.example.aftas.services.CompetitionServiceImp;
import com.example.aftas.services.interfaces.CompetitionService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/competition")
@AllArgsConstructor
public class CompetitionController {

    private final CompetitionService competitionService;

    @GetMapping("")
    public ResponseEntity<List<CompetitionDto>> getAll(){
        return ResponseEntity.ok(this.competitionService.getAll());
    }

    @GetMapping("/ongoing")
    public ResponseEntity<List<CompetitionDto>> getOnGoingCompetitions(){
        return ResponseEntity.ok(this.competitionService.getOnGoingCompetitions());
    }

    @GetMapping("/closed")
    public ResponseEntity<List<CompetitionDto>> getClosedCompetitions(){
        return ResponseEntity.ok(this.competitionService.getClosedCompetitions());
    }

    @PostMapping("")
    @PreAuthorize("hasAnyRole('ROLE_MANAGER','ROLE_JURY' ) and hasAuthority('CAN_MANAGE_COMPETITION')")
    public ResponseEntity<CompetitionDto> add(@Valid @RequestBody CompetitionDto competitionDto){
         return ResponseEntity.status(HttpStatus.CREATED).body(this.competitionService.save(competitionDto));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_MANAGER','ROLE_JURY' ) and hasAuthority('CAN_MANAGE_COMPETITION')")
    public ResponseEntity<CompetitionDto> update(@PathVariable Long id,@Valid @RequestBody CompetitionDto competitionDto){
        competitionDto.setId(id);
        return ResponseEntity.status(HttpStatus.OK).body(this.competitionService.save(competitionDto));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_MANAGER','ROLE_JURY' ) and hasAuthority('CAN_MANAGE_COMPETITION')")
    public ResponseEntity<String> delete(@PathVariable Long id){
        this.competitionService.delete(id);
        return ResponseEntity.ok("Competition successfully deleted !");
    }

}
