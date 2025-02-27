package com.flight.thebrew.api.dto;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@EqualsAndHashCode // чтобы их можно было сравнивать
@AllArgsConstructor
public class GetCoffeeDTO {
    private Long id;
    private String name;
    private Integer amount;
    private Integer cost;
}
