package com.fashion_shop.controller;

import com.fashion_shop.model.Order;
import com.fashion_shop.model.dto.OrderUpdateReqDto;
import com.fashion_shop.service.OrderService;
import com.fashion_shop.validation.dto.OrderDtoValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("get-all")
    ResponseEntity<List<Order>> getAll(){
        return  ResponseEntity.ok(orderService.getAll());
    }

    @GetMapping("/{id}")
    ResponseEntity<Order> getById(@PathVariable("id") Long id){
        return ResponseEntity.ok(orderService.getById(id));
    }

    @PostMapping
    ResponseEntity<Order> create(@RequestBody Order order){
        return ResponseEntity.ok(orderService.create(order));
    }

    @PutMapping("/{idOrder}")
    ResponseEntity<Order> update(@PathVariable("idOrder") Long id, @RequestBody OrderUpdateReqDto reqDto){
        if(!OrderDtoValidator.chekOrderUpdateDto(reqDto)){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "user data is invalid to update users order"
            );
        }
        return ResponseEntity.ok(orderService.update(reqDto, id));
    }

    @DeleteMapping("/{idOrder}")
    ResponseEntity<Void> delete(@PathVariable("idOrder") Long id){
        orderService.delete(id);
        return ResponseEntity.ok().build();
    }

}
