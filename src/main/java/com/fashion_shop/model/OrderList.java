package com.fashion_shop.model;

import com.fashion_shop.model.commons.enums.OrderStatus;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

@Data
@ToString
@Entity

//@Table(name="car")
public class OrderList {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long date;

//    @JoinColumn(name = "prduct_id_order")
    @OneToOne
    private Product product;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;
}
