package com.flight.thebrew.core.service.impl;

import com.flight.thebrew.api.dto.CreateCoffeeDTO;
import com.flight.thebrew.api.dto.GetCoffeeDTO;
import com.flight.thebrew.core.Coffee;
import com.flight.thebrew.core.repository.CoffeeRepository;
import com.flight.thebrew.core.service.CoffeeService;
import com.flight.thebrew.core.service.mapper.coffee.CreateCoffeeMapper;
import com.flight.thebrew.core.service.mapper.coffee.GetCoffeeMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CoffeeServiceImpl implements CoffeeService {
    private final GetCoffeeMapper getCoffeeMapper;
    private final CreateCoffeeMapper createCoffeeMapper;
    private final CoffeeRepository coffeeRepository;

    @Override
    public GetCoffeeDTO getById(long id) {
        return getCoffeeMapper.toDto(findById(id));
    }

    @Override
    public List<GetCoffeeDTO> getByCost(int cost) {
        List<Coffee> coffeeList = coffeeRepository.findByCost(cost);

        return getCoffeeMapper.toDtos(coffeeList);
    }

    @Override
    public GetCoffeeDTO create(CreateCoffeeDTO createCoffeeDTO) {
        Coffee coffee = createCoffeeMapper.toEntity(createCoffeeDTO);
        coffeeRepository.save(coffee);

        return getCoffeeMapper.toDto(coffee);
    }

    @Override
    public GetCoffeeDTO update(long id, CreateCoffeeDTO createCoffeeDTO) {
        Coffee coffee = findById(id);
        Coffee updatedCoffee = createCoffeeMapper.merge(coffee, createCoffeeDTO);
        coffeeRepository.save(updatedCoffee);

        return getCoffeeMapper.toDto(updatedCoffee);
    }

    @Override
    public void delete(long id) {
        Coffee coffee = findById(id);

        coffeeRepository.delete(coffee);
    }

    private Coffee findById(long id) {
        return coffeeRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Coffee with id " + id + " not found")
        );
    }
}
