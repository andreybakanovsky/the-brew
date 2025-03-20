package com.flight.thebrew.core.service.impl;

import com.flight.thebrew.api.dto.CreateCoffeeDTO;
import com.flight.thebrew.api.dto.GetCoffeeDTO;
import com.flight.thebrew.core.entity.Coffee;
import com.flight.thebrew.core.repository.CoffeeRepository;
import com.flight.thebrew.core.service.mapper.coffee.CreateCoffeeMapper;
import com.flight.thebrew.core.service.mapper.coffee.GetCoffeeMapper;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
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
    void testGetByIdWhenCoffeeExists() {
        long coffeeId = 4L;

        Coffee coffeeEntity = new Coffee(4L, "Cappuccino", 250, BigDecimal.valueOf(5.5));
        GetCoffeeDTO getCoffeeDTO = new GetCoffeeDTO(4L, "Cappuccino", 250, BigDecimal.valueOf(5.5));

        when(coffeeRepository.findById(coffeeId)).thenReturn(Optional.of(coffeeEntity));
        when(getCoffeeMapper.toDto(coffeeEntity)).thenReturn(getCoffeeDTO);

        GetCoffeeDTO result = coffeeService.getById(coffeeId);

        verify(coffeeRepository).findById(coffeeId);
        verify(getCoffeeMapper).toDto(coffeeEntity);

        assertEquals(getCoffeeDTO, result);

    }

    @Test
    void testGetByIdWhenCoffeeNotFound() {
        long coffeeId = 37L;

        when(coffeeRepository.findById(coffeeId)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> coffeeService.getById(coffeeId));

        verify(coffeeRepository).findById(coffeeId);
        verify(getCoffeeMapper, never()).toDto(any());
    }

    @Test
    void testGetByCostWhereSuchCoffeeExists() {
        BigDecimal coffeeCost = BigDecimal.valueOf(5.5);

        Coffee coffeeEntity1 = new Coffee(4L, "Cappuccino", 250, BigDecimal.valueOf(5.5));
        Coffee coffeeEntity2 = new Coffee(5L, "Espresso", 100, BigDecimal.valueOf(5.5));

        GetCoffeeDTO coffeeDtoEntity1 = new GetCoffeeDTO(4L, "Cappuccino", 250, BigDecimal.valueOf(5.5));
        GetCoffeeDTO coffeeDtoEntity2 = new GetCoffeeDTO(5L, "Espresso", 100, BigDecimal.valueOf(5.5));

        List<Coffee> coffeeList = List.of(coffeeEntity1, coffeeEntity2);
        List<GetCoffeeDTO> coffeeDtoList = List.of(coffeeDtoEntity1, coffeeDtoEntity2);

        when(coffeeRepository.findByCost(coffeeCost)).thenReturn(coffeeList);
        when(getCoffeeMapper.toDtos(coffeeList)).thenReturn(coffeeDtoList);

        List<GetCoffeeDTO> result = coffeeService.getByCost(coffeeCost);

        verify(coffeeRepository).findByCost(coffeeCost);

        assertEquals(coffeeDtoList, result);
    }

    @Test
    void testGetByCostWhereNoCoffeeFound() {
        BigDecimal coffeeCost = BigDecimal.valueOf(10.0);

        when(coffeeRepository.findByCost(coffeeCost)).thenReturn(List.of());
        when(getCoffeeMapper.toDtos(List.of())).thenReturn(List.of());

        List<GetCoffeeDTO> result = coffeeService.getByCost(coffeeCost);

        verify(coffeeRepository).findByCost(coffeeCost);
        verify(getCoffeeMapper).toDtos(List.of());

        assertTrue(result.isEmpty());
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

        verify(coffeeRepository).save(coffeeEntity);
        verify(createCoffeeMapper).toEntity(createCoffeeDTO);
        verify(getCoffeeMapper).toDto(coffeeEntity);

        assertEquals(getCoffeeDTO, result);
    }

    @Test
    void testUpdateCoffee() {
        long coffeeId = 5L;
        CreateCoffeeDTO createCoffeeDTO = new CreateCoffeeDTO("Cappuccino", 250, BigDecimal.valueOf(5.5));

        Coffee coffee = new Coffee(5L, "Cappuccinooooooooooo", 250, BigDecimal.valueOf(5.5));
        Coffee updatedCoffee = new Coffee(5L, "Cappuccino", 250, BigDecimal.valueOf(7.5));
        GetCoffeeDTO getCoffeeDTO = new GetCoffeeDTO(5L, "Cappuccino", 250, BigDecimal.valueOf(7.5));

        when(coffeeRepository.findById(coffeeId)).thenReturn(Optional.of(coffee));
        when(createCoffeeMapper.merge(coffee, createCoffeeDTO)).thenReturn(updatedCoffee);
        when(coffeeRepository.save(updatedCoffee)).thenReturn(updatedCoffee);
        when(getCoffeeMapper.toDto(updatedCoffee)).thenReturn(getCoffeeDTO);

        GetCoffeeDTO result = coffeeService.update(coffeeId, createCoffeeDTO);

        verify(coffeeRepository).findById(coffeeId);
        verify(createCoffeeMapper).merge(coffee, createCoffeeDTO);
        verify(coffeeRepository).save(updatedCoffee);
        verify(getCoffeeMapper).toDto(updatedCoffee);

        assertEquals(getCoffeeDTO, result);
    }

    @Test
    void testUpdateCoffeeWhenCoffeeNotFound() {
        long coffeeId = 37L;
        CreateCoffeeDTO createCoffeeDTO = new CreateCoffeeDTO("Cappuccino", 250, BigDecimal.valueOf(5.5));

        when(coffeeRepository.findById(coffeeId)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> coffeeService.update(coffeeId, createCoffeeDTO));

        verify(coffeeRepository).findById(coffeeId);
        verify(createCoffeeMapper, never()).merge(any(), any());
        verify(coffeeRepository, never()).save(any());
        verify(getCoffeeMapper, never()).toDto(any());
    }

    @Test
    void testDeleteCoffee() {
        long coffeeId = 5L;
        Coffee coffee = new Coffee(5L, "Cappuccino", 250, BigDecimal.valueOf(7.5));

        when(coffeeRepository.findById(coffeeId)).thenReturn(Optional.of(coffee));

        coffeeService.delete(coffeeId);

        verify(coffeeRepository).findById(coffeeId);
        verify(coffeeRepository).delete(coffee);
    }

    @Test
    void testDeleteCoffeeWhenCoffeeNotFound() {
        long coffeeId = 37L;

        when(coffeeRepository.findById(coffeeId)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> coffeeService.delete(coffeeId));

        verify(coffeeRepository).findById(coffeeId);
        verify(coffeeRepository, never()).delete(any());
    }
}

