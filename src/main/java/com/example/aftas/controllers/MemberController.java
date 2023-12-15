package com.example.aftas.controllers;
import com.example.aftas.dto.MemberDto;
import com.example.aftas.entities.Member;
import com.example.aftas.repositories.MemberRepository;
import com.example.aftas.services.MemberService;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/member")
public class MemberController {

    ModelMapper modelMapper = new ModelMapper();
    MemberService memberService;

    MemberController(MemberService memberService){this.memberService=memberService;}

    @GetMapping("")
    public List<Member> getAll(){return this.memberService.getAll();}

    @GetMapping("/{id}")
    public Member find(@PathVariable Long id){return this.memberService.find(id);}

    @PostMapping("")
    public Member add(@RequestBody MemberDto memberDto){
        Member member = modelMapper.map(memberDto, Member.class);
        return this.memberService.save(member);
    }

    @PutMapping("/{id}")
    public Member update(@PathVariable Long id, @RequestBody MemberDto memberDto){
        Member member = modelMapper.map(memberDto, Member.class);
        member.setId(id);
        return this.memberService.save(member);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){this.memberService.delete(id);}
}
