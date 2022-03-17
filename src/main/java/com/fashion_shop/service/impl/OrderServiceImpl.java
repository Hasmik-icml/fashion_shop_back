package com.fashion_shop.service.impl;

import com.fashion_shop.model.Order;
import com.fashion_shop.model.dto.OrderUpdateReqDto;
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
    public List<Order> getAll() {
        return orderRepository.findAll();
    }

    @Override
    public Order getById(Long id) {
        return orderRepository.getById(id);
    }

    @Override
    public Order create(Order order) {
        return orderRepository.save(order);
    }

    @Override
    @Transactional
    public Order update(OrderUpdateReqDto reqDto, Long id) {
        Order dbOrder = orderRepository.findById(id).orElseThrow(()->{
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"wrong");
        });
        dbOrder.setOrderStatus(reqDto.getOrderStatus());
        dbOrder.setCount(reqDto.getCount());

        return dbOrder;
    }

    @Override
    public void delete(Long id) {
        orderRepository.deleteById(id);
    }
}
