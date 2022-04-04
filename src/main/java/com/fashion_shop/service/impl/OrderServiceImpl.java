package com.fashion_shop.service.impl;

import com.fashion_shop.model.Order;
import com.fashion_shop.model.commons.enums.OrderStatus;
import com.fashion_shop.repository.OrderRepository;
import com.fashion_shop.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;

    /***
     *
     * @param order the product that would be added in DB
     * @return new product which has added
     */
    @Override
    public Order create(Order order) {
        return orderRepository.save(order);
    }

    @Override
    @Transactional
    public void changeStatus(Long orderId, OrderStatus orderStatus) {
        Order fromDb = orderRepository.findById(orderId).orElseThrow(() -> new ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                "Order with id:" + orderId + "  not found in database"));
        fromDb.setOrderStatus(orderStatus);
    }

    /***
     *
     * @return all data from DB, if there is not any data will return empty List.
     */
    @Override
    public List<Order> getAll() {
        return orderRepository.findAll();
    }

    /***
     *
     * @param id with the help of it will find the object from DB.
     * @return returns founded object or throws @ResponseStatusException(BAD_REQUEST).
     */
    @Override
    public List<Order> getAllById(String id) {
        return orderRepository.getAllByUserId(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.BAD_REQUEST,
                        "Orders with user_id:" + id + "  not found in database")
                );
    }

    /***
     *
     * @param id with the help of it will find the object from DB.
     * @return returns founded object or throws @ResponseStatusException(BAD_REQUEST).
     */
//    @Override
//    public Order getById(Long id) {
//        return orderRepository.findById(id).orElseThrow(()-> {
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
//                    "product with id:" + id + "  not found in database");
//        });
//
//    }


    /***
     *
     * @param id is related to order which need to update
     * @param reqDto is changed data
     * @returns just updated order
     */
//    @Override
//    @Transactional
//    public Order update(OrderUpdateReqDto reqDto, String id) {
//        Order dbOrder = orderRepository.findById(id).orElseThrow(()->{
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"product with id:" + id + "  not found in database");
//        });
//        dbOrder.setOrderStatus(reqDto.getOrderStatus());
//        dbOrder.setCount(reqDto.getCount());

//        return null;
//    }
    @Override
    public List<Order> getOrderByStatus(String userId, OrderStatus orderStatus) {
        return getAllById(userId).stream()
                .filter(item -> item.getOrderStatus() == orderStatus)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        orderRepository.deleteById(id);
    }
}
