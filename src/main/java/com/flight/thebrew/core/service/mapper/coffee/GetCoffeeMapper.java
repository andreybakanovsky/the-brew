package com.flight.thebrew.core.service.mapper.coffee;

import com.flight.thebrew.api.dto.GetCoffeeDTO;
import com.flight.thebrew.core.entity.Coffee;
import com.flight.thebrew.core.service.mapper.BaseMapper;
import org.mapstruct.Mapper;

@Mapper(config = BaseMapper.class)
public interface GetCoffeeMapper extends BaseMapper<Coffee, GetCoffeeDTO>{
}
