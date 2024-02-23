package com.example.aftas.controllers;

import com.example.aftas.dto.Fish.RequestFishDto;
import com.example.aftas.dto.Fish.ResponseFishDto;
import com.example.aftas.services.interfaces.FishService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/fish")
public class FishController {
    private final FishService fishService;

    @GetMapping("")
    @PreAuthorize("hasAnyRole('ROLE_MANAGER','ROLE_JURY' ) and hasAuthority('CAN_MANAGE_FISH')")
    public List<ResponseFishDto> getAll(){return this.fishService.getAll();}

    @PostMapping("")
    @PreAuthorize("hasAnyRole('ROLE_MANAGER','ROLE_JURY' ) and hasAuthority('CAN_MANAGE_FISH')")
    public ResponseEntity<ResponseFishDto> add(@Valid @RequestBody RequestFishDto requestFishDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(this.fishService.save(requestFishDto));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_MANAGER','ROLE_JURY' ) and hasAuthority('CAN_MANAGE_FISH')")
    public ResponseEntity<ResponseFishDto> update(@PathVariable Long id, @Valid @RequestBody RequestFishDto requestFishDto){
        requestFishDto.setId(id);
        return ResponseEntity.ok(this.fishService.save(requestFishDto));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_MANAGER','ROLE_JURY' ) and hasAuthority('CAN_MANAGE_FISH')")
    public ResponseEntity<String> delete(@PathVariable Long id){
        this.fishService.delete(id);
        return ResponseEntity.ok("Fish successfully deleted !");
    }
}
