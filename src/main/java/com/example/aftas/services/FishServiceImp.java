package com.example.aftas.services;


import com.example.aftas.dto.Fish.RequestFishDto;
import com.example.aftas.dto.Fish.ResponseFishDto;
import com.example.aftas.entities.Fish;
import com.example.aftas.entities.Level;
import com.example.aftas.repositories.FishRepository;
import com.example.aftas.services.interfaces.FishService;
import com.example.aftas.services.interfaces.LevelService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FishServiceImp implements FishService {
    private final FishRepository fishRepository;
    private final LevelService levelService;
    private final ModelMapper modelMapper;

    public List<ResponseFishDto> getAll(){
        List<Fish> fishList = this.fishRepository.findAll();
        return fishList.stream().map(f->modelMapper.map(f, ResponseFishDto.class)).collect(Collectors.toList());
    }

    public Fish find(Long id) throws EntityNotFoundException {
        Optional<Fish> fish = this.fishRepository.findById(id);
        if(!fish.isPresent()) throw new EntityNotFoundException("Fish not found !");
        return fish.get();
    }

    public ResponseFishDto save(RequestFishDto requestFishDto) {
        Level level = this.levelService.find(requestFishDto.getLevel_id());
        Fish fish = new Fish().builder()
                .name(requestFishDto.getName())
                .avgWeight(requestFishDto.getAvgWeight())
                .level(level)
                .build();
        this.fishRepository.save(fish);
        ResponseFishDto responseFishDto = modelMapper.map(level, ResponseFishDto.class);
        return responseFishDto;
    }

    public void delete(Long id){
         Fish fish = this.find(id);
         this.fishRepository.delete(fish);
    }


}
