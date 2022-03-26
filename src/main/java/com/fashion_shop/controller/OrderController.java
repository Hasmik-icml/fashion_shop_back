package com.fashion_shop.controller;

import com.fashion_shop.model.Order;
import com.fashion_shop.model.dto.OrderUpdateReqDto;
import com.fashion_shop.service.OrderService;
import com.fashion_shop.validation.OrderValidator;
import com.fashion_shop.validation.UserValidator;
import com.fashion_shop.validation.dto.OrderDtoValidator;
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

//    @GetMapping("get-all")
//    ResponseEntity<List<Order>> getAll(){
//        return  ResponseEntity.ok(orderService.getAll());
//    }

    @GetMapping("/{user_order}")
    ResponseEntity<List<Order>> getById(@PathVariable("user_id") String userId){
        if (!UserValidator.checkUserAuthorized(userId)){
            throw new ResponseStatusException(
                    HttpStatus.UNAUTHORIZED,
                    "user is UNAUTHORIZED, plz SignUp at first"
            );
        }
        return ResponseEntity.ok(orderService.getAllById(userId));
    }

    @PostMapping
    ResponseEntity<Order> create(@RequestBody Order order){
        if (!OrderValidator.validateOrder(order)){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Invalid order Structure for accepting Order"
            );
        }
        return ResponseEntity.ok(orderService.create(order));
    }

    @PutMapping("/{user_id}/{order_id}")
    ResponseEntity<Order> update(@PathVariable("user_id") String userId, @PathVariable("order_id") String orderId,  @RequestBody OrderUpdateReqDto reqDto){//???
        if(!OrderDtoValidator.chekOrderUpdateDto(reqDto)){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "user data is invalid to update users order"
            );
        }
        if (!UserValidator.checkUserAuthorized(userId)) {
            throw new ResponseStatusException(
                    HttpStatus.UNAUTHORIZED,
                    "user is UNAUTHORIZED, plz AUTHORIZE at first"
            );
        }
        return ResponseEntity.ok(orderService.update(reqDto, orderId));
    }

    @DeleteMapping("/{idOrder}")
    ResponseEntity<Void> delete(@PathVariable("idOrder") Long id){
        orderService.delete(id);
        return ResponseEntity.ok().build();
    }

}
