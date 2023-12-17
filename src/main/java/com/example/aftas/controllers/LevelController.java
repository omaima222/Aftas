package com.example.aftas.controllers;

import com.example.aftas.dto.LevelDto;
import com.example.aftas.entities.Level;
import com.example.aftas.services.LevelService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/level")
public class LevelController {

    LevelService levelService;
    ModelMapper modelMapper = new ModelMapper();

    LevelController(LevelService levelService){this.levelService=levelService;}

    @GetMapping("")
    public List<Level> getAll(){return this.levelService.getALl();}

    @GetMapping("/{id}")
    public Level find(@PathVariable Long id){return  this.levelService.find(id);}

    @PostMapping("")
    public Level save(@Valid @RequestBody LevelDto levelDto){
        Level level = modelMapper.map(levelDto, Level.class);
        return this.levelService.save(level);
    }

    @PutMapping("/{id}")
    public Level update(@PathVariable Long id,@Valid @RequestBody LevelDto levelDto){
        Level level = modelMapper.map(levelDto, Level.class);
        level.setId(id);
        return this.levelService.save(level);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        this.levelService.delete(id);
    }
}
