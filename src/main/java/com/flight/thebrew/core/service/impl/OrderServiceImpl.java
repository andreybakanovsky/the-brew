package com.flight.thebrew.core.service.impl;

import com.flight.thebrew.api.dto.CreateOrderDTO;
import com.flight.thebrew.api.dto.GetOrderDTO;
import com.flight.thebrew.core.entity.Order;
import com.flight.thebrew.core.repository.OrderRepository;
import com.flight.thebrew.core.service.OrderService;
import com.flight.thebrew.core.service.mapper.order.CreateOrderMapper;
import com.flight.thebrew.core.service.mapper.order.GetOrderMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderServiceImpl implements OrderService {
    private final CreateOrderMapper createOrderMapper;
    private final OrderRepository orderRepository;
    private final GetOrderMapper getOrderMapper;

    public List<GetOrderDTO> getAllOrders() {
        List<Order> orders = orderRepository.findAll();

        return getOrderMapper.toDtos(orders);
    }

    public GetOrderDTO getById(long id) {
        return getOrderMapper.toDto(findById(id));
    }

    public GetOrderDTO create(CreateOrderDTO createOrderDTO) {
        Order order = createOrderMapper.toEntity(createOrderDTO);
        orderRepository.save(order);

        return getOrderMapper.toDto(order);
    }

    public void delete(long id) {
        orderRepository.delete(findById(id));
    }

    private Order findById(long id) {
        return orderRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Order with id " + id + " not found")
        );
    }
}
