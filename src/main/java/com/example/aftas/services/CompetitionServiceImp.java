package com.example.aftas.services;

import com.example.aftas.dto.CompetitionDto;
import com.example.aftas.dto.Ranking.ResponseRankingDto;
import com.example.aftas.entities.Competition;
import com.example.aftas.entities.Ranking;
import com.example.aftas.repositories.CompetitionRepository;
import com.example.aftas.repositories.RankingRepository;
import com.example.aftas.services.interfaces.CompetitionService;
import com.example.aftas.services.interfaces.RankingService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CompetitionServiceImp implements CompetitionService {
    private final CompetitionRepository competitionRepository;
    private final ModelMapper modelMapper;


    public List<CompetitionDto> getClosedCompetitions(){
        List<CompetitionDto> allCompetitions = this.getAll();
        List<CompetitionDto> onGoingCompetitions =this.getOnGoingCompetitions();
        List<CompetitionDto> closedCompetitions = new ArrayList<>(allCompetitions);
        closedCompetitions.removeAll(onGoingCompetitions);
        return closedCompetitions;
    }

    public List<CompetitionDto> getOnGoingCompetitions(){
        List<Competition> onGoingCompetitions = this.competitionRepository.getOnGoingCompetitions(LocalDate.now(), LocalTime.now());
        return onGoingCompetitions.stream().map(c->modelMapper.map(c, CompetitionDto.class)).collect(Collectors.toList());
    }

    public List<CompetitionDto> getAll(){
        List<Competition> competitions = this.competitionRepository.findAll();
        return competitions.stream().map(c->modelMapper.map(c, CompetitionDto.class)).collect(Collectors.toList());
    }

    public Competition find(Long id) throws EntityNotFoundException {
        Optional<Competition> competition = this.competitionRepository.findById(id);
        if(!competition.isPresent()) throw new EntityNotFoundException("Competition not found !");
        return competition.get();
    }

    public CompetitionDto save(CompetitionDto competitionDto) throws ValidationException {
        List<CompetitionDto> competitions = this.getAll();

        for (CompetitionDto cmp : competitions) {
            if (cmp.getDate().equals(competitionDto.getDate())) {
                throw new ValidationException("There can't be two competitions on the same day !") ;
            }
        }
        Competition competition = modelMapper.map(competitionDto, Competition.class);
        this.competitionRepository.save(competition);
        return competitionDto;
    }

    public void delete(Long id){
        Competition competition = this.find(id);
        this.competitionRepository.delete(competition);
    }

}
