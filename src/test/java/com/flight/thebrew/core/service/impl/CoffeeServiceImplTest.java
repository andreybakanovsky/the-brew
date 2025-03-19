package com.flight.thebrew.core.service.impl;

import com.flight.thebrew.api.dto.CreateCoffeeDTO;
import com.flight.thebrew.api.dto.GetCoffeeDTO;
import com.flight.thebrew.core.entity.Coffee;
import com.flight.thebrew.core.repository.CoffeeRepository;
import com.flight.thebrew.core.service.mapper.coffee.CreateCoffeeMapper;
import com.flight.thebrew.core.service.mapper.coffee.GetCoffeeMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class CoffeeServiceImplTest {

    CoffeeServiceImpl coffeeService;
    private GetCoffeeMapper getCoffeeMapper;
    private CreateCoffeeMapper createCoffeeMapper;
    CoffeeRepository coffeeRepository;

    @BeforeEach
    void init() {
        getCoffeeMapper = mock(GetCoffeeMapper.class);
        createCoffeeMapper = mock(CreateCoffeeMapper.class);
        coffeeRepository = mock(CoffeeRepository.class);

        coffeeService = new CoffeeServiceImpl(getCoffeeMapper, createCoffeeMapper, coffeeRepository);
    }


    @Test
    void testCreateCoffee() {
        CreateCoffeeDTO createCoffeeDTO = new CreateCoffeeDTO("Cappuccino", 250, BigDecimal.valueOf(5.5));
        Coffee coffeeEntity = new Coffee(2L, "Cappuccino", 250, BigDecimal.valueOf(5.5));
        GetCoffeeDTO getCoffeeDTO = new GetCoffeeDTO(2L, "Cappuccino", 250, BigDecimal.valueOf(5.5));

        when(createCoffeeMapper.toEntity(createCoffeeDTO)).thenReturn(coffeeEntity);
        when(coffeeRepository.save(coffeeEntity)).thenReturn(coffeeEntity);
        when(getCoffeeMapper.toDto(coffeeEntity)).thenReturn(getCoffeeDTO);

        GetCoffeeDTO result = coffeeService.create(createCoffeeDTO);

        verify(coffeeRepository, times(1)).save(coffeeEntity);
        verify(createCoffeeMapper, times(1)).toEntity(createCoffeeDTO);
        verify(getCoffeeMapper, times(1)).toDto(coffeeEntity);

        assertEquals(getCoffeeDTO, result);
    }

}

