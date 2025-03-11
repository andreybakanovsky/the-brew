package com.flight.thebrew.core.service.mapper.order;

import com.flight.thebrew.api.dto.CreateOrderDTO;
import com.flight.thebrew.core.entity.Order;
import com.flight.thebrew.core.service.mapper.BaseMapper;
import org.mapstruct.Mapper;

@Mapper(config = BaseMapper.class)
public interface CreateOrderMapper extends BaseMapper<Order, CreateOrderDTO> {
}
