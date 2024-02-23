package com.example.aftas.services;


import com.example.aftas.config.JwtService;
import com.example.aftas.dto.AuthDto;
import com.example.aftas.dto.MemberDto;
import com.example.aftas.dto.TokenDto;
import com.example.aftas.entities.Member;
import com.example.aftas.repositories.MemberRepository;
import com.example.aftas.repositories.RoleRepository;
import com.example.aftas.services.interfaces.AuthService;
import com.example.aftas.services.interfaces.MemberService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ValidationException;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MemberServiceImp implements MemberService, AuthService {
    private final MemberRepository memberRepository;
    private final RoleRepository roleRepository;
    private final ModelMapper modelMapper;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public List<MemberDto> getAll(){
        List<Member> members = this.memberRepository.findAll();
        return members.stream().map(m->modelMapper.map(m, MemberDto.class)).collect(Collectors.toList());
    }
    public List<MemberDto> getMemberByLastName(String lastName){
        List<Member> members = this.memberRepository.findMemberByLastName(lastName);
        return members.stream().map(m->modelMapper.map(m, MemberDto.class)).collect(Collectors.toList());
    }
    public List<MemberDto> getMemberByFirstName(String firstName){
        List<Member> members = this.memberRepository.findMemberByFirstName(firstName);
        return members.stream().map(m->modelMapper.map(m, MemberDto.class)).collect(Collectors.toList());
    }
    public List<MemberDto> getMemberByIdentityNumber(String identityNumber){
        List<Member> members = this.memberRepository.findMemberByIdentityNumber(identityNumber);
        return members.stream().map(m->modelMapper.map(m, MemberDto.class)).collect(Collectors.toList());
    }

    public Member find(Long id) throws EntityNotFoundException {
        Optional<Member> member = this.memberRepository.findById(id);
        if(!member.isPresent()) throw new EntityNotFoundException("Member not found !");
        return member.get();
    }
    public Member findMemberByEmail(String email) throws EntityNotFoundException{
        Optional<Member> member = this.memberRepository.findMemberByEmail(email);
        if(!member.isPresent()) throw new EntityNotFoundException("Member not found !");
        return member.get();
    }

    public Member save(MemberDto memberDto){
        memberDto.setAccessDate(new Date());
        System.out.println("role name: "+memberDto.getRole());
        Member member = modelMapper.map(memberDto, Member.class);
        System.out.println("role : "+member.getRole());
        member.setRole(this.roleRepository.findRoleByName(memberDto.getRole()));
        member.setPassword(this.passwordEncoder.encode(memberDto.getPassword()));
        this.memberRepository.save(member);
        return member;
    }

    public MemberDto update(MemberDto memberDto){
        Member member = this.find(memberDto.getId());
        this.save(memberDto);
        return modelMapper.map(member, MemberDto.class);
    }

    public TokenDto signup(MemberDto memberDto) throws ValidationException {
        Optional<Member> existingMember = this.memberRepository.findMemberByEmail(memberDto.getEmail());
        if(existingMember.isPresent()) throw new ValidationException("This Email is already taken !");
        Member member = this.save(memberDto);
        String jwt = this.jwtService.generateToken(member);
        return new TokenDto(jwt);
    }

    public TokenDto login(AuthDto authDto) {
        this.authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authDto.getEmail(), authDto.getPassword())
        );
        Member member = this.findMemberByEmail(authDto.getEmail());
        String token = jwtService.generateToken(member);
        return new TokenDto(token);
    }
    public void delete(Long id){
        Member member = this.find(id);
        this.memberRepository.delete(member);
    }

}
