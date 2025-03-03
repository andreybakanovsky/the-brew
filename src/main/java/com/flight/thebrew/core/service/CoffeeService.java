package com.flight.thebrew.core.service;

import com.flight.thebrew.api.dto.CreateCoffeeDTO;
import com.flight.thebrew.api.dto.GetCoffeeDTO;

import java.util.List;

public interface CoffeeService {

    GetCoffeeDTO getById(long id);

    List<GetCoffeeDTO> getByCost(int cost);

    GetCoffeeDTO create(CreateCoffeeDTO createCoffeeDTO);

    GetCoffeeDTO update(long id, CreateCoffeeDTO createCoffeeDTO);

    void delete(long id);
}
