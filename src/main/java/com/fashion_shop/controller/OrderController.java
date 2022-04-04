package com.fashion_shop.controller;

import com.fashion_shop.model.Order;
import com.fashion_shop.model.commons.enums.OrderStatus;
import com.fashion_shop.model.dto.ResponseDto;
import com.fashion_shop.service.OrderService;
import com.fashion_shop.validation.OrderValidator;
import com.fashion_shop.validation.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/v1/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("get-all")
    ResponseEntity<List<Order>> getAll() {
        return ResponseEntity.ok(orderService.getAll());
    }

    @GetMapping("/user-order")
    ResponseEntity<List<Order>> getOrdersByUserId(@RequestHeader("user_id") String userId) {
        if (!UserValidator.checkUserAuthorized(userId)) {
            throw new ResponseStatusException(
                    HttpStatus.UNAUTHORIZED,
                    "user is UNAUTHORIZED, plz SignUp at first"
            );
        }
        return ResponseEntity.ok(orderService.getAllById(userId));
    }

    @GetMapping("/order-status")
    ResponseEntity<List<Order>> getOrderByStatus(@RequestHeader("user_id") String userId,
                                                 @RequestHeader("status") OrderStatus orderStatus) {
        if (!UserValidator.checkUserAuthorized(userId)) {
            throw new ResponseStatusException(
                    HttpStatus.UNAUTHORIZED,
                    "user is UNAUTHORIZED, plz SignUp at first"
            );
        }

        return ResponseEntity.ok(orderService.getOrderByStatus(userId, orderStatus));
    }

    @PutMapping("/change-status/{order_id}/{status}")
    ResponseEntity<ResponseDto> changeStatus(@RequestHeader("user_id") String userId,
                                             @PathVariable("order_id") Long orderId,
                                             @PathVariable("status") OrderStatus orderStatus) {
        if (!UserValidator.checkUserAuthorized(userId)) {
            throw new ResponseStatusException(
                    HttpStatus.UNAUTHORIZED,
                    "user is UNAUTHORIZED, plz SignUp at first"
            );
        }
        orderService.changeStatus(orderId, orderStatus);
        ResponseDto responseDto = new ResponseDto("OrderStatus changed.");
        responseDto.addInfo("OrderStatus", String.valueOf(orderId));
        return ResponseEntity.ok(responseDto);
    }


    @PostMapping
    ResponseEntity<ResponseDto> create(@RequestBody Order order) {
        if (!OrderValidator.validateOrder(order)) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Invalid order Structure for accepting Order"
            );
        }
        Order created = orderService.create(order);
        ResponseDto responseDto = new ResponseDto("Order created.");
        responseDto.addInfo("OrderId", String.valueOf(created.getId()));
        return ResponseEntity.ok(responseDto);
    }
//    @PutMapping("/{user_id}/{order_id}")
//    Order update(@PathVariable("user_id") String userId, @PathVariable("order_id") String orderId, OrderUpdateReqDto reqDto) {
//        if (!OrderDtoValidator.checkOrderUpdateDto(reqDto)) {
//            throw new ResponseStatusException(
//                    HttpStatus.BAD_REQUEST,
//                    "user data is invalid to update users order"
//            );
//        }
//        if (!UserValidator.checkUserAuthorized(userId)) {
//            throw new ResponseStatusException(
//                    HttpStatus.UNAUTHORIZED,
//                    "user is UNAUTHORIZED, plz AUTHORIZE at first"
//            );
//        }
//        return orderService.update(reqDto, orderId);
//    }

//    @PutMapping("/{user_id}/{order_id}")
//    ResponseEntity<Order> update(@PathVariable("user_id") String userId, @PathVariable("order_id") String orderId,  @RequestBody OrderUpdateReqDto reqDto){//???
//        if(!OrderDtoValidator.chekOrderUpdateDto(reqDto)){
//            throw new ResponseStatusException(
//                    HttpStatus.BAD_REQUEST,
//                    "user data is invalid to update users order"
//            );
//        }
//        if (!UserValidator.checkUserAuthorized(userId)) {
//            throw new ResponseStatusException(
//                    HttpStatus.UNAUTHORIZED,
//                    "user is UNAUTHORIZED, plz AUTHORIZE at first"
//            );
//        }
//        return ResponseEntity.ok(orderService.update(reqDto, orderId));
//    }

    @DeleteMapping("/{idOrder}")
    ResponseEntity<ResponseDto> delete(@PathVariable("idOrder") Long id) {

        orderService.delete(id);
        ResponseDto responseDto = new ResponseDto("Order deleted.");
        responseDto.addInfo("OrderId", String.valueOf(id));
        return ResponseEntity.ok(responseDto);
    }

}

