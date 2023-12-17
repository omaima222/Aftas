package com.example.aftas.repositories;

import com.example.aftas.entities.Competition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Repository
public interface CompetitionRepository extends JpaRepository<Competition, Long> {
    @Query("select c from Competition c where c.date = :today and c.endTime > :now and c.startTime < :now  ")
    public List<Competition> getOnGoingCompetitions(LocalDate today, LocalTime now);
}
