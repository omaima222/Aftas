package com.example.aftas.controllers;

import com.example.aftas.dto.FishDto;
import com.example.aftas.entities.Fish;
import com.example.aftas.services.FishService;
import com.example.aftas.services.LevelService;
import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/fish")
public class FishController {
    ModelMapper modelMapper = new ModelMapper();
    FishService fishService;

    FishController(FishService fishService){this.fishService=fishService;}

    @GetMapping("")
    public List<Fish> getAll(){return this.fishService.getAll();}

    @GetMapping("/{id}")
    public Fish find(@PathVariable Long id){return this.fishService.find(id);}

    @PostMapping("")
    public Fish add(@RequestBody FishDto fishDto){
        Fish fish = modelMapper.map(fishDto, Fish.class);
        return this.fishService.save(fish, fishDto.getLevel_id());
    }

    @PutMapping("/{id}")
    public Fish update(@PathVariable Long id, @RequestBody FishDto fishDto){
        Fish fish = modelMapper.map(fishDto, Fish.class);
        fish.setId(id);
        return this.fishService.save(fish, fishDto.getLevel_id());
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){this.fishService.delete(id);}
}
