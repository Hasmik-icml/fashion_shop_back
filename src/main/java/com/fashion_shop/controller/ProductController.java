package com.fashion_shop.controller;

import com.fashion_shop.model.Product;
import com.fashion_shop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("get-all")
    ResponseEntity<List<Product>> getAll() {
        return ResponseEntity.ok(productService.getAll());
    }
    @GetMapping("/{anytext}")
    ResponseEntity<List<Product>> getByAnyText(@PathVariable("anytext") String anytext){
        return ResponseEntity.ok(productService.getByAnyText(anytext));
    }
    @PostMapping
    ResponseEntity<Product> create(@RequestBody Product product){
        return ResponseEntity.ok(productService.create(product));
    }
    @PutMapping("/{id}")
    ResponseEntity<Product> update(@PathVariable("id") Long id, @RequestBody Product product){
        return  ResponseEntity.ok(productService.update(product,id));
    }
    @DeleteMapping("/{id}")
    ResponseEntity<Void> delete(@PathVariable("id") long id){
        productService.delete(id);
        return  ResponseEntity.ok().build();
    }

}
