package com.flight.thebrew.core.service.impl;

import com.flight.thebrew.api.dto.CreateCoffeeDTO;
import com.flight.thebrew.api.dto.GetCoffeeDTO;
import com.flight.thebrew.core.Coffee;
import com.flight.thebrew.core.service.CoffeeService;
import com.flight.thebrew.core.service.mapper.coffee.CreateCoffeeMapper;
import com.flight.thebrew.core.service.mapper.coffee.GetCoffeeMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class CoffeeServiceImpl implements CoffeeService {

    private final GetCoffeeMapper getCoffeeMapper;
    private final CreateCoffeeMapper createCoffeeMapper;
    private final List<Coffee> cacheRepository = new ArrayList<>(
            List.of(
                    Coffee.builder().id(1L).name("Arabica").amount(1).cost(10).build(),
                    Coffee.builder().id(2L).name("Robusta").amount(1).cost(20).build()
            )
    );

    @Override
    public GetCoffeeDTO getById(long id) {
        try {
            return getCoffeeMapper.toDto(findById(id));
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @Override
    public List<GetCoffeeDTO> getByCost(int cost) {
        return getCoffeeMapper.toDtos(
                cacheRepository.stream()
                        .filter((coffee) -> coffee.getCost().equals(cost))
                        .collect(Collectors.toList())
        );
    }

    @Override
    public GetCoffeeDTO create(CreateCoffeeDTO createCoffeeDTO) {
        cacheRepository.add(createCoffeeMapper.toEntity(createCoffeeDTO));
        return getCoffeeMapper.toDto(createCoffeeMapper.toEntity(createCoffeeDTO));
    }

    @Override
    public GetCoffeeDTO update(long id, CreateCoffeeDTO createCoffeeDTO) {
        Coffee coffee = findById(id);
        Coffee savedCoffee = createCoffeeMapper.merge(coffee, createCoffeeDTO);
        return getCoffeeMapper.toDto(savedCoffee);
    }

    @Override
    public void delete(long id) {
       cacheRepository.remove(findById(id));
    }

    private Coffee findById(long id) {
        return cacheRepository.stream()
                .filter(coffee -> coffee.getId() == id)
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Coffee with id " + id + " not found"));
    }
}
