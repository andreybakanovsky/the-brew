package com.flight.thebrew.core.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "order_items", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"order_id", "coffee_id"})
})
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @ManyToOne
    @JoinColumn(name = "coffee_id", nullable = false)
    private Coffee coffee;

    @Column(nullable = false, columnDefinition = "INT DEFAULT 1")
    private Integer quantity;
}
