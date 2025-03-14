package com.flight.thebrew.api.dto;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@EqualsAndHashCode
@AllArgsConstructor
public class GetOrderItemDTO {
    private Long id;
    private GetCoffeeDTO coffee;
    private Integer quantity;
}
