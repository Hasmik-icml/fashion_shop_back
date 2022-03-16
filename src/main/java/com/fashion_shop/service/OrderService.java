package com.fashion_shop.service;

import com.fashion_shop.model.OrderList;

import java.util.List;

public interface OrderService {
    List<OrderList> getAll();

    OrderList create(OrderList order);

    OrderList update(OrderList order, Long id);

    void delete(Long id);
}
