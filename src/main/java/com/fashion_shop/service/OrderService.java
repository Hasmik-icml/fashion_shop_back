package com.fashion_shop.service;

import com.fashion_shop.model.Order;
import com.fashion_shop.model.commons.enums.OrderStatus;
import com.fashion_shop.model.dto.OrderUpdateReqDto;

import java.util.List;

public interface OrderService {

    List<Order> getAll();

    List<Order> getOrderByStatus(String userId, OrderStatus orderStatus);

    void delete(Long id);

    List<Order> getAllById(String id);

    Order create(Order order);


    void changeStatus(Long orderId, OrderStatus orderStatus);
}
