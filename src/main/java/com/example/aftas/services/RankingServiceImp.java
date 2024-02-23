package com.example.aftas.services;

import com.example.aftas.dto.Ranking.RequestRankingDto;
import com.example.aftas.dto.Ranking.ResponseRankingDto;
import com.example.aftas.entities.*;
import com.example.aftas.repositories.CompetitionRepository;
import com.example.aftas.repositories.HuntingRepository;
import com.example.aftas.repositories.RankingRepository;
import com.example.aftas.services.interfaces.CompetitionService;
import com.example.aftas.services.interfaces.MemberService;
import com.example.aftas.services.interfaces.RankingService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RankingServiceImp implements RankingService {
    private final RankingRepository rankingRepository;
    private final HuntingRepository huntingRepository;
    private final CompetitionService competitionService;
    private final MemberService memberService;
    private final ModelMapper modelMapper;

    public List<ResponseRankingDto> getAll(){
        List<Ranking> rankings = this.rankingRepository.findAll();
        return rankings.stream().map(r->modelMapper.map(r, ResponseRankingDto.class)).collect(Collectors.toList());
    }

    public Ranking find(Long id) throws EntityNotFoundException {
        Optional<Ranking> ranking = this.rankingRepository.findById(id);
        if(!ranking.isPresent()) throw new EntityNotFoundException("Ranking not found !");
        return ranking.get();
    }

    public ResponseRankingDto register(RequestRankingDto requestRankingDto) throws ValidationException {
        Competition competition = this.competitionService.find(requestRankingDto.getCompetition_id());
        if(LocalDate.now().isAfter(competition.getDate().minusDays(1))){
            throw new ValidationException("Registration must be done 1 day before the competition !");
        }
        List<Ranking> rankings = this.rankingRepository.findRankingByCompetition(competition);
        if (rankings.size() >= competition.getParticipantsNum()) {
            throw new ValidationException("All places are taken !");
        }
        Member member = this.memberService.find(requestRankingDto.getMember_id());
        Ranking existingRanking = this.rankingRepository.findRankingByCompetitionAndMember(competition, member);
        if(existingRanking!=null){
            throw new ValidationException("Member already registered !") ;
        }
        Ranking ranking = Ranking.builder()
                .score(0)
                .rank(0)
                .competition(competition)
                .member(member)
                .build();
        this.rankingRepository.save(ranking);
        return modelMapper.map(ranking, ResponseRankingDto.class);
    }

    public void updateScore(Ranking ranking){
        Integer score = calculateScore(this.huntingRepository.findHuntingByCompetitionAndMember(ranking.getCompetition(), ranking.getMember()));
        ranking.setScore(score);
        this.rankingRepository.save(ranking);
    }
    public List<Ranking> updateRanking(Competition competition){
        List<Ranking> rankings = competition.getRankings();
        for(Ranking ranking : rankings){
            Integer rank = calculateRank(ranking.getScore(), competition);
            ranking.setRank(rank);
            this.rankingRepository.save(ranking);
        }
        rankings = this.rankingRepository.findRankingByCompetitionOrderByRankAsc(competition);
        if(rankings.size()>3){
            rankings.stream().limit(3).collect(Collectors.toList());
        }
        return rankings;
    }
    public Integer calculateScore(List<Hunting> huntings){
        Map<Level, Integer> levelAndFishCount = new HashMap<>();

        for (Hunting hunting : huntings) {
            Fish fish = hunting.getFish();
            Level level = fish.getLevel();
            levelAndFishCount.put(level, levelAndFishCount.getOrDefault(level, 0) + hunting.getFishNum());
        }

        Integer totalScore = 0;

        for (Map.Entry<Level, Integer> entry : levelAndFishCount.entrySet()) {
            Level level = entry.getKey();
            int fishCount = entry.getValue();
            totalScore += level.getPoints() * fishCount;
        }

        return totalScore;
    }
    public Integer calculateRank(Integer score, Competition competition){
        List<Ranking> rankings = this.rankingRepository.findRankingByCompetitionOrderByScoreDesc(competition);
        Integer rank= 1;
        for (Ranking ranking : rankings) {
            if (score < ranking.getScore()) {
                rank++;
            } else {
                break;
            }
        }
        return rank;
    }

    public void delete(Long id){
        Ranking ranking = this.find(id);
        this.rankingRepository.delete(ranking);
    }

    public List<ResponseRankingDto> endCompetition(Long id) {
        Competition competition = this.competitionService.find(id);
        List<Ranking> rankings = this.rankingRepository.findRankingByCompetition(competition);
        for (Ranking ranking : rankings) {
            this.updateScore(ranking);
            this.updateRanking(competition);
        }
        return rankings.stream().map(r->modelMapper.map(r, ResponseRankingDto.class)).collect(Collectors.toList());
    }

}
