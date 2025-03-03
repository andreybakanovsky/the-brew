package com.flight.thebrew.api.controller;

import com.flight.thebrew.api.dto.CreateCoffeeDTO;
import com.flight.thebrew.api.dto.GetCoffeeDTO;
import com.flight.thebrew.core.service.CoffeeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/coffee")
public class CoffeeController {

    private final CoffeeService coffeeService;

    @GetMapping("/{id}")
    public GetCoffeeDTO getById(@PathVariable long id) {
        log.info("Get by id= {}", id);
        return coffeeService.getById(id);
    }

    @GetMapping
    public List<GetCoffeeDTO> getByCost(@RequestParam int cost) {
        log.info("Get by cost= {}", cost);
        return coffeeService.getByCost(cost);
    }

    @PostMapping
    public GetCoffeeDTO create(@RequestBody CreateCoffeeDTO createCoffeeDTO) {
        return coffeeService.create(createCoffeeDTO);
    }

    @PutMapping("/{id} ")
    public GetCoffeeDTO update(@PathVariable long id, @RequestBody CreateCoffeeDTO createCoffeeDTO) {
        return coffeeService.update(id, createCoffeeDTO);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id) {
        coffeeService.delete(id);
        log.info("Object with id= {} was successfully removed.", id);
    }
}
