package com.fashion_shop.service.impl;

import com.fashion_shop.model.OrderList;
import com.fashion_shop.repository.OrderRepository;
import com.fashion_shop.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Override
    public List<OrderList> getAll() {
        return orderRepository.findAll();
    }

    @Override
    public OrderList create(OrderList order) {
        return orderRepository.save(order);
    }

    @Override
    @Transactional
    public OrderList update(OrderList order, Long id) {
        OrderList dbOrder = orderRepository.findById(id).orElseThrow(()->{
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"wrong");
        });
        dbOrder.setOrderStatus(order.getOrderStatus());
        dbOrder.setDate(order.getDate());
        dbOrder.setProduct(order.getProduct());
        return dbOrder;
    }

    @Override
    public void delete(Long id) {
        orderRepository.deleteById(id);
    }
}
