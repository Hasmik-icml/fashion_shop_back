package com.fashion_shop.controller;

import com.fashion_shop.model.Product;
import com.fashion_shop.model.dto.ResponseDto;
import com.fashion_shop.service.ImageService;
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
    @Autowired
    private ImageService imageService;

    @GetMapping("get-all")
    ResponseEntity<List<Product>> getAll() {
        return ResponseEntity.ok(productService.getAll());
    }

    @GetMapping("/{id}")
    ResponseEntity<Product> getById(@PathVariable long id){
        return ResponseEntity.ok(productService.getById(id));
    }

    @PostMapping
    ResponseEntity<ResponseDto> create(@RequestBody Product product){
        if(!ProductValidator.validateCreateProduct(product)){
            throw  new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "user data is invalid to create product");
        }
        Product created = productService.create(product);
        ResponseDto responseDto = new ResponseDto("Product created.");
        responseDto.addInfo("productId", String.valueOf(created.getId()));
        return ResponseEntity.ok(responseDto);
    }

    @PutMapping("/{id}")
    ResponseEntity<ResponseDto> update(@PathVariable Long id, @RequestBody Product product){
        if (!ProductValidator.validateUpdateProduct(product)) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "user data is invalid to update product with id:" + id
            );
        }
        Product updated = productService.update(product, id);
        ResponseDto responseDto = new ResponseDto("Product updated.");
        responseDto.addInfo("productId", String.valueOf(updated.getId()));
        return ResponseEntity.ok(responseDto);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<ResponseDto> delete(@PathVariable long id){
        productService.delete(id);
        ResponseDto responseDto = new ResponseDto("Product deleted.");
        responseDto.addInfo("productId", String.valueOf(id));
        return ResponseEntity.ok(responseDto);
    }

}
