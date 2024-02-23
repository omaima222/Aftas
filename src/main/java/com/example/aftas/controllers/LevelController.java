package com.example.aftas.controllers;

import com.example.aftas.dto.LevelDto;
import com.example.aftas.entities.Level;
import com.example.aftas.services.LevelServiceImp;
import com.example.aftas.services.interfaces.LevelService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/level")
@RequiredArgsConstructor
public class LevelController {

    private final LevelService levelService;

    @GetMapping("")
    @PreAuthorize("hasAnyRole('ROLE_MANAGER','ROLE_JURY' ) and hasAuthority('CAN_MANAGE_LEVEL')")
    public List<LevelDto> getAll(){return this.levelService.getALl();}

    @PostMapping("")
    @PreAuthorize("hasAnyRole('ROLE_MANAGER','ROLE_JURY' ) and hasAuthority('CAN_MANAGE_LEVEL')")
    public ResponseEntity<LevelDto> save(@Valid @RequestBody LevelDto levelDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(this.levelService.save(levelDto));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_MANAGER','ROLE_JURY' ) and hasAuthority('CAN_MANAGE_LEVEL')")
    public ResponseEntity<LevelDto> update(@PathVariable Long id,@Valid @RequestBody LevelDto levelDto){
        levelDto.setId(id);
        return ResponseEntity.ok(this.levelService.save(levelDto));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_MANAGER','ROLE_JURY' ) and hasAuthority('CAN_MANAGE_LEVEL')")
    public ResponseEntity<String> delete(@PathVariable Long id){
        this.levelService.delete(id);
        return ResponseEntity.ok("Level deleted successfully !");
    }
}
