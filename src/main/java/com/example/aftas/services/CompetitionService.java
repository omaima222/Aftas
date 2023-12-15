package com.example.aftas.services;

import com.example.aftas.dto.CompetitionDto;
import com.example.aftas.entities.Competition;
import com.example.aftas.repositories.CompetitionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompetitionService {
    CompetitionRepository competitionRepository;

    CompetitionService(CompetitionRepository competitionRepository){
        this.competitionRepository = competitionRepository;
    }

    public List<Competition> getAll(){return this.competitionRepository.findAll();}

    public Competition find(Long id){return this.competitionRepository.findById(id).get();}

    public Competition save(Competition competition){return this.competitionRepository.save(competition);}

    public void delete(Long id){
        Competition competition = this.find(id);
        this.competitionRepository.delete(competition);
    }

}
