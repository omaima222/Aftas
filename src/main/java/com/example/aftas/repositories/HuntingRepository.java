package com.example.aftas.repositories;

import com.example.aftas.entities.Competition;
import com.example.aftas.entities.Fish;
import com.example.aftas.entities.Hunting;
import com.example.aftas.entities.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HuntingRepository extends JpaRepository<Hunting, Long> {
    public Hunting findHuntingByCompetitionAndFishAndMember(Competition competition, Fish fish, Member member);
    public List<Hunting> findHuntingByCompetitionAndMember(Competition competition, Member member);
}
