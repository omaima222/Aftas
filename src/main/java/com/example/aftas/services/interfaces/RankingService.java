package com.example.aftas.services.interfaces;

import com.example.aftas.dto.Ranking.RequestRankingDto;
import com.example.aftas.dto.Ranking.ResponseRankingDto;
import com.example.aftas.entities.Competition;
import com.example.aftas.entities.Ranking;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ValidationException;

import java.util.List;

public interface RankingService {
    public List<ResponseRankingDto> getAll();
    public Ranking find(Long id) throws EntityNotFoundException;
    public ResponseRankingDto register(RequestRankingDto requestRankingDto) throws ValidationException;
    public void updateScore(Ranking ranking);
    public List<Ranking> updateRanking(Competition competition);
    public void delete(Long id);
    public List<ResponseRankingDto> endCompetition(Long id);

}
