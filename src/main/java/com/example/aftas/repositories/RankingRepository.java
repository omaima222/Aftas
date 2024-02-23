package com.example.aftas.repositories;

import com.example.aftas.entities.Competition;
import com.example.aftas.entities.Member;
import com.example.aftas.entities.Ranking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RankingRepository extends JpaRepository<Ranking, Long> {
    public Ranking findRankingByCompetitionAndMember(Competition competition, Member member);

    public  List<Ranking> findRankingByCompetition(Competition competition);

    public List<Ranking> findRankingByCompetitionOrderByScoreDesc(Competition competition);

    public List<Ranking> findRankingByCompetitionOrderByRankAsc(Competition competition);
}
