package com.fashion_shop.controller;

import com.fashion_shop.model.OrderList;
import com.fashion_shop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("get-all")
    ResponseEntity<List<OrderList>> getAll(){
        return  ResponseEntity.ok(orderService.getAll());
    }
    @PostMapping
    ResponseEntity<OrderList> create(@RequestBody OrderList order){
        return ResponseEntity.ok(orderService.create(order));
    }


    @PutMapping("/{idOrder}")
    ResponseEntity<OrderList> update(@PathVariable("idOrder") Long id, @RequestBody OrderList order){
        return ResponseEntity.ok(orderService.update(order, id));
    }
    @DeleteMapping("/{idOrder}")
    ResponseEntity<Void> delete(@PathVariable("idOrder") Long id){
        orderService.delete(id);
        return ResponseEntity.ok().build();
    }

}
