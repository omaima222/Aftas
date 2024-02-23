package com.example.aftas.services;
import com.example.aftas.dto.Hunting.RequestHuntingDto;
import com.example.aftas.dto.Hunting.ResponseHuntingDto;
import com.example.aftas.entities.Competition;
import com.example.aftas.entities.Fish;
import com.example.aftas.entities.Hunting;
import com.example.aftas.entities.Member;
import com.example.aftas.repositories.HuntingRepository;
import com.example.aftas.services.interfaces.CompetitionService;
import com.example.aftas.services.interfaces.FishService;
import com.example.aftas.services.interfaces.MemberService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HuntingServiceImp {
    private final HuntingRepository huntingRepository;
    private final CompetitionService competitionService;
    private final MemberService memberService;
    private final FishService fishService;
    private final ModelMapper modelMapper;

    public List<ResponseHuntingDto> getAll(){
        List<Hunting> huntings = this.huntingRepository.findAll();
        return huntings.stream().map(h->modelMapper.map(h, ResponseHuntingDto.class)).collect(Collectors.toList());
    }

    public Hunting find(Long id) throws EntityNotFoundException {
        Optional<Hunting> hunting = this.huntingRepository.findById(id);
        if(!hunting.isPresent()) throw new EntityNotFoundException("Hunting not found !");
        return hunting.get();
    }

    public ResponseHuntingDto save(RequestHuntingDto requestHuntingDto){
        Competition competition = this.competitionService.find(requestHuntingDto.getCompetition_id());
        Member member = this.memberService.find(requestHuntingDto.getMember_id());
        Fish fish = this.fishService.find(requestHuntingDto.getFish_id());
        Hunting existingHunting = this.huntingRepository.findHuntingByCompetitionAndFishAndMember(competition, fish, member);
        if(existingHunting!=null){
            existingHunting.setFishNum(existingHunting.getFishNum()+requestHuntingDto.getFishNum());
            this.huntingRepository.save(existingHunting);
            return modelMapper.map(existingHunting, ResponseHuntingDto.class);
        }
        Hunting hunting = Hunting.builder()
                .fishNum(requestHuntingDto.getFishNum())
                .fish(fish)
                .competition(competition)
                .member(member)
                .build();
        return modelMapper.map(hunting, ResponseHuntingDto.class);
    }

    public void delete(Long id){
        Hunting hunting = this.find(id);
        this.huntingRepository.delete(hunting);
    }


}


