package com.flight.thebrew.core.service.impl;

import com.flight.thebrew.api.dto.CreateCoffeeDTO;
import com.flight.thebrew.api.dto.GetCoffeeDTO;
import com.flight.thebrew.core.entity.Coffee;
import com.flight.thebrew.core.repository.CoffeeRepository;
import com.flight.thebrew.core.service.mapper.coffee.CreateCoffeeMapper;
import com.flight.thebrew.core.service.mapper.coffee.GetCoffeeMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CoffeeServiceImplTest {
    @Mock
    private GetCoffeeMapper getCoffeeMapper;

    @Mock
    private CreateCoffeeMapper createCoffeeMapper;

    @Mock
    private CoffeeRepository coffeeRepository;

    @InjectMocks
    private CoffeeServiceImpl coffeeService;

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

