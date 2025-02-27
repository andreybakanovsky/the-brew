package com.flight.thebrew.core.service.mapper.coffee;

import com.flight.thebrew.api.dto.CreateCoffeeDTO;
import com.flight.thebrew.core.Coffee;
import com.flight.thebrew.core.service.mapper.BaseMapper;
import org.mapstruct.Mapper;

@Mapper(config = BaseMapper.class)
public interface CreateCoffeeMapper extends BaseMapper<Coffee, CreateCoffeeDTO> {
}
