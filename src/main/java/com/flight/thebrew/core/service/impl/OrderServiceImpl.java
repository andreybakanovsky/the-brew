package com.flight.thebrew.core.service.impl;

import com.flight.thebrew.api.dto.CreateOrderDTO;
import com.flight.thebrew.api.dto.GetOrderDTO;
import com.flight.thebrew.core.entity.Coffee;
import com.flight.thebrew.core.entity.Order;
import com.flight.thebrew.core.entity.OrderItem;
import com.flight.thebrew.core.repository.CoffeeRepository;
import com.flight.thebrew.core.repository.OrderRepository;
import com.flight.thebrew.core.service.OrderService;
import com.flight.thebrew.core.service.mapper.order.GetOrderMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final GetOrderMapper getOrderMapper;
    private final CoffeeRepository coffeeRepository;

    public List<GetOrderDTO> getAllOrders() {
        List<Order> orders = orderRepository.findAll();

        return getOrderMapper.toDtos(orders);
    }

    public GetOrderDTO getById(long id) {
        return getOrderMapper.toDto(findById(id));
    }

    @Transactional
    public GetOrderDTO create(CreateOrderDTO createOrderDTO) {
        Order order = new Order();
        order.setCustomerName(createOrderDTO.getCustomerName());

        List<OrderItem> orderItems = createOrderDTO.getOrderItems().stream()
                .map(item -> {
                    Coffee coffee = findCoffeeById(item.getCoffeeId());

                    OrderItem orderItem = new OrderItem();
                    orderItem.setOrder(order);
                    orderItem.setCoffee(coffee);
                    orderItem.setQuantity(item.getQuantity());
                    return orderItem;
                }).collect(Collectors.toList());
        order.setOrderItems(orderItems);
        Order finalOrder = orderRepository.save(order);

        return getOrderMapper.toDto(finalOrder);
    }

    public void delete(long id) {
        orderRepository.delete(findById(id));
    }

    private Order findById(long id) {
        return orderRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Order with id " + id + " not found")
        );
    }

    private Coffee findCoffeeById(long id) {
        return coffeeRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Coffee with id " + id + " not found")
        );
    }
}
