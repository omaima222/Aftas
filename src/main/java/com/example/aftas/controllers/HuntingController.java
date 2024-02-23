package com.example.aftas.controllers;

import com.example.aftas.dto.Hunting.RequestHuntingDto;
import com.example.aftas.dto.Hunting.ResponseHuntingDto;
import com.example.aftas.entities.Hunting;
import com.example.aftas.services.HuntingServiceImp;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/hunting")
@RequiredArgsConstructor
public class HuntingController {
    private final HuntingServiceImp huntingService;

    @GetMapping("")
    @PreAuthorize("hasAnyRole('ROLE_MANAGER','ROLE_JURY' ) and hasAuthority('CAN_MANAGE_HUNTING')")
    public List<ResponseHuntingDto> getAll(){return this.huntingService.getAll();}

    @PostMapping("")
    @PreAuthorize("hasAnyRole('ROLE_MANAGER','ROLE_JURY' ) and hasAuthority('CAN_MANAGE_HUNTING')")
    public ResponseEntity<ResponseHuntingDto> add(@Valid @RequestBody RequestHuntingDto requestHuntingDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(this.huntingService.save(requestHuntingDto));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_MANAGER','ROLE_JURY' ) and hasAuthority('CAN_MANAGE_HUNTING')")
    public ResponseEntity<ResponseHuntingDto> update(@PathVariable Long id,@Valid @RequestBody RequestHuntingDto requestHuntingDto){
        requestHuntingDto.setId(id);
        return ResponseEntity.status(HttpStatus.CREATED).body(this.huntingService.save(requestHuntingDto));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_MANAGER','ROLE_JURY' ) and hasAuthority('CAN_MANAGE_HUNTING')")
    public ResponseEntity<String> delete(@PathVariable Long id){
        this.huntingService.delete(id);
        return ResponseEntity.ok("Hunting successfully deleted !");
    }
}
