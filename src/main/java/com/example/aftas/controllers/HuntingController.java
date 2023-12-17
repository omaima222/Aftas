package com.example.aftas.controllers;

import com.example.aftas.dto.HuntingDto;
import com.example.aftas.entities.Hunting;
import com.example.aftas.entities.Level;
import com.example.aftas.services.HuntingService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/hunting")
public class HuntingController {
    ModelMapper modelMapper = new ModelMapper();
    HuntingService huntingService;

    HuntingController(HuntingService huntingService){
        this.huntingService=huntingService;
    }

    @GetMapping("")
    public List<Hunting> getAll(){return this.huntingService.getAll();}

    @GetMapping("/{id}")
    public Hunting find(@PathVariable Long id){return  this.huntingService.find(id);}

    @PostMapping("")
    public Hunting save(@Valid @RequestBody HuntingDto huntingDto){
        Hunting hunting = modelMapper.map(huntingDto, Hunting.class);
        return this.huntingService.save(hunting, huntingDto.getCompetition_id(), huntingDto.getFish_id(), huntingDto.getMember_id());
    }

    @PutMapping("/{id}")
    public Hunting update(@PathVariable Long id,@Valid @RequestBody HuntingDto huntingDto){
        Hunting hunting = modelMapper.map(huntingDto, Hunting.class);
        hunting.setId(id);
        return this.huntingService.save(hunting, huntingDto.getCompetition_id(), huntingDto.getFish_id(), huntingDto.getMember_id());
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        this.huntingService.delete(id);
    }
}
