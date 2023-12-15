package com.example.aftas.services;


import com.example.aftas.entities.Fish;
import com.example.aftas.entities.Level;
import com.example.aftas.repositories.FishRepository;
import com.example.aftas.repositories.LevelRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FishService {
    FishRepository fishRepository;
    LevelService levelService;

    FishService(FishRepository fishRepository, LevelService levelService){
        this.fishRepository=fishRepository;
        this.levelService=levelService;
    }

    public List<Fish> getAll(){return  this.fishRepository.findAll();}

    public Fish find(Long id){return  this.fishRepository.findById(id).get();}

    public Fish save(Fish fish, Long level_id){
        Level level = this.levelService.find(level_id);
        fish.setLevel(level);
        return  this.fishRepository.save(fish);
    }

    public void delete(Long id){
         Fish fish = this.find(id);
         this.fishRepository.delete(fish);
    }



}
