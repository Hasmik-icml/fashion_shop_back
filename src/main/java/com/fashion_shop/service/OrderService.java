package com.fashion_shop.service;

import com.fashion_shop.model.Order;
import com.fashion_shop.model.dto.OrderUpdateReqDto;

import java.util.List;

public interface OrderService {

    List<Order> getAll();

    List<Order> getAllById(String id);

    Order create(Order order);

    Order update(OrderUpdateReqDto order, String id);

    void delete(Long id);
}
