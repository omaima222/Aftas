package com.example.aftas.services.interfaces;

import com.example.aftas.dto.Fish.RequestFishDto;
import com.example.aftas.dto.Fish.ResponseFishDto;
import com.example.aftas.entities.Fish;
import jakarta.persistence.EntityNotFoundException;

import java.util.List;

public interface FishService {
    public List<ResponseFishDto> getAll();
    public Fish find(Long id) throws EntityNotFoundException;
    public ResponseFishDto save(RequestFishDto requestFishDto);
    public void delete(Long id);
}
