package com.example.aftas.services.interfaces;

import com.example.aftas.dto.CompetitionDto;
import com.example.aftas.dto.Ranking.ResponseRankingDto;
import com.example.aftas.entities.Competition;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ValidationException;

import java.util.List;

public interface CompetitionService {
    public List<CompetitionDto> getClosedCompetitions();
    public List<CompetitionDto> getOnGoingCompetitions();
    public List<CompetitionDto> getAll();
    public Competition find(Long id) throws EntityNotFoundException;
    public CompetitionDto save(CompetitionDto competitionDto) throws ValidationException;
    public void delete(Long id);

}
