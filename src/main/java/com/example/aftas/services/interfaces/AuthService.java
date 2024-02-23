package com.example.aftas.services.interfaces;

import com.example.aftas.dto.AuthDto;
import com.example.aftas.dto.MemberDto;
import com.example.aftas.dto.TokenDto;
import org.apache.catalina.authenticator.DigestAuthenticator;

public interface AuthService {
    public TokenDto login(AuthDto authDto);
    public TokenDto signup(MemberDto memberDto);
}
