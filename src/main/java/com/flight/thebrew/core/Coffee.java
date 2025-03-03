package com.flight.thebrew.core;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
@AllArgsConstructor
public class Coffee {
    private Long id;

    private String name;

    private Integer amount;

    private Integer cost;
}
