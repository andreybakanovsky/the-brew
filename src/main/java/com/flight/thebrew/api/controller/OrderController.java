package com.flight.thebrew.api.controller;

import com.flight.thebrew.api.dto.CreateOrderDTO;
import com.flight.thebrew.api.dto.GetOrderDTO;
import com.flight.thebrew.core.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    @GetMapping()
    public List<GetOrderDTO> getAllOrders() {
        return orderService.getAllOrders();
    }

    @GetMapping("/{id}")
    public GetOrderDTO getById(@PathVariable long id) {
        return orderService.getById(id);
    }

    @PostMapping
    private GetOrderDTO create(@RequestBody CreateOrderDTO createOrderDTO) {
        return orderService.create(createOrderDTO);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id) {
        orderService.delete(id);
    }
}
