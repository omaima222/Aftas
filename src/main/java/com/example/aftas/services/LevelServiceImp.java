package com.example.aftas.services;

import com.example.aftas.dto.LevelDto;
import com.example.aftas.entities.Level;
import com.example.aftas.repositories.LevelRepository;
import com.example.aftas.services.interfaces.LevelService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LevelServiceImp implements LevelService {

    private final LevelRepository levelRepository;
    private final ModelMapper modelMapper;

    public List<LevelDto> getALl(){
        List<Level> levels = this.levelRepository.findAll();
        return levels.stream().map(l->modelMapper.map(l,LevelDto.class)).collect(Collectors.toList());
    }
    public Level find(Long id) throws EntityNotFoundException {
        Optional<Level> level =this.levelRepository.findById(id);
        if(!level.isPresent()) throw new EntityNotFoundException("Level not found !");
        return level.get();
    }
    public LevelDto save(LevelDto levelDto){
        Level level = modelMapper.map(levelDto, Level.class);
        this.levelRepository.save(level);
        return levelDto;
    }

    public void delete(Long id){
        Level level = this.find(id);
        this.levelRepository.delete(level);
    }
}
