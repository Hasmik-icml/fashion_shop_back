package com.fashion_shop.model;

import com.fashion_shop.model.commons.enums.OrderStatus;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

@Data
@ToString
@Entity

@Table(name="user_orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long date;

    private Integer count;

    @OneToOne
    private Product product;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @ManyToOne
    private User user;
}
