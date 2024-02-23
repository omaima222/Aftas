package com.example.aftas.controllers;

import com.example.aftas.dto.AuthDto;
import com.example.aftas.dto.MemberDto;
import com.example.aftas.dto.TokenDto;
import com.example.aftas.services.interfaces.MemberService;
import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final MemberService memberService;

    @PostMapping("register")
    public ResponseEntity<TokenDto> register(@Valid @RequestBody MemberDto memberDto) {
        return ResponseEntity.ok(memberService.signup(memberDto));
    }


    @PostMapping("authenticate")
    public ResponseEntity<TokenDto> authenticate(@Valid @RequestBody AuthDto authDto) {
        return ResponseEntity.ok(memberService.login(authDto));
    }
}
