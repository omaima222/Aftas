package com.example.aftas.services;

import com.example.aftas.entities.Competition;
import com.example.aftas.entities.Hunting;
import com.example.aftas.entities.Member;
import com.example.aftas.entities.Ranking;
import com.example.aftas.repositories.RankingRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RankingService {
    RankingRepository rankingRepository;
    CompetitionService competitionService;
    MemberService memberService;

    RankingService(RankingRepository rankingRepository, CompetitionService competitionService, MemberService memberService){
        this.rankingRepository=rankingRepository;
        this.competitionService=competitionService;
        this.memberService=memberService;
    }

    public List<Ranking> getAll(){return this.rankingRepository.findAll();}

    public Ranking find(Long id){return this.rankingRepository.findById(id).get();}

    public Ranking save(Ranking ranking, Long competition_id, Long member_id){
        Competition competition = this.competitionService.find(competition_id);
        Member member = this.memberService.find(member_id);
        ranking.setCompetition(competition);
        ranking.setMember(member);
        return this.rankingRepository.save(ranking);
    }

    public void delete(Long id){
        Ranking ranking = this.find(id);
        this.rankingRepository.delete(ranking);
    }

}
