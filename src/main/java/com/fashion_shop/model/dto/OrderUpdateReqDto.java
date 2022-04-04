package com.fashion_shop.model.dto;

import com.fashion_shop.model.commons.enums.OrderStatus;
import lombok.Data;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Data
@ToString
public class OrderUpdateReqDto {

    private Integer count;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;
}
