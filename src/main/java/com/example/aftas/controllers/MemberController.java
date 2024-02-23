package com.example.aftas.controllers;
import com.example.aftas.dto.MemberDto;
import com.example.aftas.entities.Member;
import com.example.aftas.services.MemberServiceImp;
import com.example.aftas.services.interfaces.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("")
    @PreAuthorize("hasAnyRole('ROLE_MANAGER', 'ROLE_JURY') ")
    public List<MemberDto> getAll(){return this.memberService.getAll();}

    @PostMapping("/firstName")
    @PreAuthorize("hasAnyRole('ROLE_MANAGER', 'ROLE_JURY') ")
    public List<MemberDto> getMemberByFirstName(@RequestBody String firstName){return this.memberService.getMemberByFirstName(firstName);}

    @PostMapping("/lastName")
    @PreAuthorize("hasAnyRole('ROLE_MANAGER', 'ROLE_JURY') ")
    public List<MemberDto> getMemberByLastName(@RequestBody String lastName){return this.memberService.getMemberByLastName(lastName);}

    @PostMapping("/identityNumber")
    @PreAuthorize("hasAnyRole('ROLE_MANAGER', 'ROLE_JURY') ")
    public List<MemberDto> getMemberByIdentityNumber(@RequestBody String identityNumber){return this.memberService.getMemberByIdentityNumber(identityNumber);}

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_MANAGER' ) and hasAuthority('CAN_MANAGE_USER')")
    public  ResponseEntity<MemberDto> update(@PathVariable Long id,@Valid @RequestBody MemberDto memberDto){
        memberDto.setId(id);
        return ResponseEntity.status(HttpStatus.OK).body(this.memberService.update(memberDto));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_MANAGER' ) and hasAuthority('CAN_MANAGE_USER')")
    public ResponseEntity<String>  delete(@PathVariable Long id){
        this.memberService.delete(id);
        return ResponseEntity.ok("Member successfully deleted !");
    }
}
