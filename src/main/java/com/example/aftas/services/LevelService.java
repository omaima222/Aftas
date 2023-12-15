package com.example.aftas.services;

import com.example.aftas.entities.Level;
import com.example.aftas.repositories.LevelRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LevelService {

    LevelRepository levelRepository;

    LevelService(LevelRepository levelRepository){this.levelRepository=levelRepository;}

    public List<Level> getALl(){return this.levelRepository.findAll();}
    public Level find(Long id){return this.levelRepository.findById(id).get();}
    public Level save(Level level){return this.levelRepository.save(level);}

    public void delete(Long id){
        Level level = this.find(id);
        this.levelRepository.delete(level);
    }
}
