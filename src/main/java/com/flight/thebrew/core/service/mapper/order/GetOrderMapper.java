package com.flight.thebrew.core.service.mapper.order;

import com.flight.thebrew.api.dto.GetOrderDTO;
import com.flight.thebrew.core.entity.Order;
import com.flight.thebrew.core.service.mapper.BaseMapper;
import com.flight.thebrew.core.service.mapper.item.GetOrderItemMapper;
import org.mapstruct.Mapper;

@Mapper(config = BaseMapper.class, uses = GetOrderItemMapper.class)
public interface GetOrderMapper extends BaseMapper<Order, GetOrderDTO> {
}
