package com.example.demo.services;

import com.example.demo.domain.Beer;
import com.example.demo.repoitories.BeerRepository;
import com.example.demo.web.controller.NotFoundException;
import com.example.demo.web.mappers.BeerMapper;
import com.example.demo.web.model.BeerDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Service("beerService")
public class BeerServiceImpl implements BeerService {

    private final BeerRepository beerRepository;
    private final BeerMapper beerMapper;

    @Override
    public BeerDto getById(UUID beerId) {
        return beerMapper.BeerToBeerDto(beerRepository.findById(beerId).orElseThrow(NotFoundException::new));
    }

    @Override
    public BeerDto saveNewBeer(BeerDto beerDto) {

        return beerMapper.BeerToBeerDto(beerRepository.save(beerMapper.BeerDtoToBeer(beerDto)));
    }

    @Override
    public BeerDto updateBeer(UUID beerId, BeerDto beerDto) {
        Beer beer = beerRepository.findById(beerId).orElseThrow(NotFoundException::new);

        beer.setBeerName(beerDto.getBeerName());
       beer.setBeerStyle(beerDto.getBeerStyle().name());
       beer.setPrice(beerDto.getPrice());
       beer.setUpc(beerDto.getUpc());

       return beerMapper.BeerToBeerDto(beerRepository.save(beer));
    }
}
