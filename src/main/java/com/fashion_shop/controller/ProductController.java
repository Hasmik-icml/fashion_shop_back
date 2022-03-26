package com.fashion_shop.controller;

import com.fashion_shop.model.Product;
import com.fashion_shop.service.ProductService;
import com.fashion_shop.validation.ProductValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/v1/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("get-all")
    ResponseEntity<List<Product>> getAll() {
        return ResponseEntity.ok(productService.getAll());
    }

    @GetMapping("/{id}")
    ResponseEntity<Product> getById(@PathVariable("id") long id){
        return ResponseEntity.ok(productService.getById(id));
    }

//    @GetMapping("/{anytext}")
//    ResponseEntity<List<Product>> getByAnyText(@PathVariable("anytext") String anytext){
//        return ResponseEntity.ok(productService.getByAnyText(anytext));
//    }

    @PostMapping
    ResponseEntity<Product> create(@RequestBody Product product){
        if(!ProductValidator.validateCreateProduct(product)){
            throw  new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "user data is invalid to create product");
        }
        return ResponseEntity.ok(productService.create(product));
    }

    @PutMapping("/{id}")
    ResponseEntity<Product> update(@PathVariable("id") Long id, @RequestBody Product product){
        if (!ProductValidator.validateUpdateProduct(product)) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "user data is invalid to update product with id:" + id
            );
        }
        return  ResponseEntity.ok(productService.update(product,id));
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> delete(@PathVariable("id") long id){
        productService.delete(id);
        return  ResponseEntity.ok().build();
    }

}
