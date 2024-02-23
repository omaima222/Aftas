package com.example.aftas.services.interfaces;

import com.example.aftas.dto.AuthDto;
import com.example.aftas.dto.MemberDto;
import com.example.aftas.dto.TokenDto;
import com.example.aftas.entities.Member;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ValidationException;

import java.util.List;

public interface MemberService {
    public List<MemberDto> getAll();
    public List<MemberDto> getMemberByLastName(String lastName);
    public List<MemberDto> getMemberByFirstName(String firstName);
    public List<MemberDto> getMemberByIdentityNumber(String identityNumber);
    public Member find(Long id) throws EntityNotFoundException;
    public Member save(MemberDto memberDto);
    public MemberDto update(MemberDto memberDto);
    public TokenDto signup(MemberDto memberDto) throws ValidationException;
    public TokenDto login(AuthDto authDto);
    public void delete(Long id);
}
