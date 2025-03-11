package com.flight.thebrew.core.service;

import com.flight.thebrew.api.dto.CreateOrderDTO;
import com.flight.thebrew.api.dto.GetOrderDTO;

import java.util.List;

public interface OrderService {

    List<GetOrderDTO> getAllOrders();

    GetOrderDTO getById(long id);

    GetOrderDTO create(CreateOrderDTO createOrderDTO);

    void delete(long id);
}
