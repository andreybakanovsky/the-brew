package com.flight.thebrew.api.dto;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@EqualsAndHashCode
@AllArgsConstructor
public class GetOrderDTO {
    private Long id;
    private String customerName;
    private LocalDateTime createdAt;
    private List<GetOrderItemDTO> orderItems;
}
