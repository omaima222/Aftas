package com.example.aftas.services;
import com.example.aftas.entities.Competition;
import com.example.aftas.entities.Fish;
import com.example.aftas.entities.Hunting;
import com.example.aftas.entities.Member;
import com.example.aftas.repositories.HuntingRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HuntingService {
    HuntingRepository huntingRepository;
    CompetitionService competitionService;
    MemberService memberService;
    FishService fishService;

    HuntingService(HuntingRepository huntingRepository, CompetitionService competitionService, MemberService memberService, FishService fishService){
        this.huntingRepository=huntingRepository;
        this.competitionService=competitionService;
        this.memberService=memberService;
        this.fishService=fishService;
    }

    public List<Hunting> getAll(){return this.huntingRepository.findAll();}

    public Hunting find(Long id){return this.huntingRepository.findById(id).get();}

    public Hunting save(Hunting hunting, Long competition_id, Long fish_id, Long member_id){
        Competition competition = this.competitionService.find(competition_id);
        Member member = this.memberService.find(member_id);
        Fish fish = this.fishService.find(fish_id);
        hunting.setCompetition(competition);
        hunting.setFish(fish);
        hunting.setMember(member);
        return this.huntingRepository.save(hunting);
    }

    public void delete(Long id){
        Hunting hunting = this.find(id);
        this.huntingRepository.delete(hunting);
    }


}


