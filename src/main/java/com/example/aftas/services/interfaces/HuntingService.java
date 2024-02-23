package com.example.aftas.services.interfaces;

import com.example.aftas.dto.Hunting.RequestHuntingDto;
import com.example.aftas.dto.Hunting.ResponseHuntingDto;
import com.example.aftas.entities.Hunting;
import jakarta.persistence.EntityNotFoundException;

import java.util.List;

public interface HuntingService {
    public List<ResponseHuntingDto> getAll();
    public Hunting find(Long id) throws EntityNotFoundException;
    public ResponseHuntingDto save(RequestHuntingDto requestHuntingDto);
    public void delete(Long id);
}
