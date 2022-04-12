package com.fashion_shop.service;

import com.fashion_shop.model.Order;
import com.fashion_shop.model.commons.enums.OrderStatus;
import com.fashion_shop.model.dto.OrderUpdateReqDto;

import javax.transaction.Transactional;
import java.util.List;

public interface OrderService {

    List<Order> getAll();

    Order getOrderById(long orderId);

    List<Order> getOrderByStatus(String userId, OrderStatus orderStatus);

    void delete(Long id);

    List<Order> getAllById(String id);

    Order create(Order order);

//    Order update(String id, OrderUpdateReqDto order);

    @Transactional
    void changeStatus(Long orderId, OrderStatus orderStatus);

}





