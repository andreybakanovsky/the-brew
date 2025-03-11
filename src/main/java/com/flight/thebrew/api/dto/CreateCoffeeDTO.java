package com.flight.thebrew.api.dto;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
@EqualsAndHashCode
@AllArgsConstructor
public class CreateCoffeeDTO {
    private String name;
    private Integer size;
    private BigDecimal cost;
}
