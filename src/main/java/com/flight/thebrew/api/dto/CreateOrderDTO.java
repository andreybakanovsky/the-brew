package com.flight.thebrew.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@EqualsAndHashCode
@AllArgsConstructor
public class CreateOrderDTO {
    @JsonProperty("customerName")
    private String customerName;

    @JsonProperty("orderItems")
    private List<CreateOrderItemDTO> orderItems;
}
