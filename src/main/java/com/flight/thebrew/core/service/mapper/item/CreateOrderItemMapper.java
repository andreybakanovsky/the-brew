package com.flight.thebrew.core.service.mapper.item;

import com.flight.thebrew.api.dto.CreateOrderItemDTO;
import com.flight.thebrew.core.entity.OrderItem;
import com.flight.thebrew.core.service.mapper.BaseMapper;
import org.mapstruct.Mapper;

@Mapper(config = BaseMapper.class)
public interface CreateOrderItemMapper extends BaseMapper<OrderItem, CreateOrderItemDTO> {
}
