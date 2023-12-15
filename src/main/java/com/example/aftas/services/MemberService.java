package com.example.aftas.services;


import com.example.aftas.entities.Member;
import com.example.aftas.repositories.MemberRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {
    MemberRepository memberRepository;

    MemberService(MemberRepository memberRepository){this.memberRepository=memberRepository;}

    public List<Member> getAll(){return this.memberRepository.findAll();}

    public Member find(Long id){return this.memberRepository.findById(id).get();}

    public Member save(Member member){return this.memberRepository.save(member);}

    public void delete(Long id){
        Member member = this.find(id);
        this.memberRepository.delete(member);
    }
}
