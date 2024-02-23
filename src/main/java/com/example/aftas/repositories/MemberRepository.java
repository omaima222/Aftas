package com.example.aftas.repositories;


import com.example.aftas.entities.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    public Optional<Member> findMemberByEmail(String email);

    public List<Member> findMemberByFirstName(String firstName);

    public List<Member> findMemberByLastName(String lastName);

    public List<Member> findMemberByIdentityNumber(String identityNumber);
}
