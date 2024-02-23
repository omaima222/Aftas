package com.example.aftas.services.interfaces;

import com.example.aftas.dto.LevelDto;
import com.example.aftas.entities.Level;
import jakarta.persistence.EntityNotFoundException;

import java.util.List;

public interface LevelService {
    public List<LevelDto> getALl();
    public Level find(Long id) throws EntityNotFoundException;
    public LevelDto save(LevelDto levelDto);
    public void delete(Long id);
}
