package com.fashion_shop.controller;

import com.fashion_shop.model.Product;
import com.fashion_shop.model.User;
import com.fashion_shop.model.dto.ResponseDto;
import com.fashion_shop.service.ImageService;
import com.fashion_shop.service.ProductService;
import com.fashion_shop.validation.ProductValidator;
import com.fashion_shop.validation.UserValidator;
import com.fashion_shop.validation.ValidationConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @Autowired
    private ImageService imageService;

    /***
     *
     * @return all products
     */
    @GetMapping("get-all")
    ResponseEntity<List<Product>> getAll() {
        return ResponseEntity.ok(productService.getAll());
    }

    /***
     *
     * @param id is used to find the corresponding product with current id
     * @return the necessary product by provided id
     */
    @GetMapping("/{id}")
    ResponseEntity<Product> getById(@PathVariable long id){
        return ResponseEntity.ok(productService.getById(id));
    }

    /***
     *
     * @param product  is made from the provided information by front-end which includes
     *                  •name
     *                  •price
     *                  •additional product details
     * @param userId property is used to determine if the user has authorisation to make changes in database
     * @return responseDto to inform front-end that process has been done successfully/ failed
     */
    @PostMapping
    ResponseEntity<ResponseDto> create(@RequestBody Product product,
                                       @RequestHeader String userId){
        UserValidator.checkUserAuthorized(userId,HttpStatus.UNAUTHORIZED, ValidationConstants.UNAUTHORIZED_ERROR);
        ProductValidator.validateCreateProduct(product, HttpStatus.BAD_REQUEST, "product data is invalid to add in DB");
        Product created = productService.create(product);
        ResponseDto responseDto = new ResponseDto("Product created.");
        responseDto.addInfo("productId", String.valueOf(created.getId()));
        return ResponseEntity.ok(responseDto);

    }

    /***
     *
     * @param id is to get the necessary product which will be updated
     * @param product is the new information to update product
     * @param userId property is used to determine if the user has authorisation to make changes in database
     * @return responseDto to inform front-end that process has been done successfully/ failed
     */
    @PutMapping("/{id}")
    ResponseEntity<ResponseDto> update(@PathVariable Long id,
                                       @RequestBody Product product,
                                       @RequestHeader String userId){
        UserValidator.checkUserAuthorized(userId,HttpStatus.UNAUTHORIZED, ValidationConstants.UNAUTHORIZED_ERROR);
        ProductValidator.validateUpdateProduct(product, HttpStatus.BAD_REQUEST, "products data that you want to update does not matches to the product structure");

        Product updated = productService.update(product, id);
        ResponseDto responseDto = new ResponseDto("Product updated.");
        responseDto.addInfo("productId", String.valueOf(updated.getId()));
        return ResponseEntity.ok(responseDto);
    }

    /***
     *
     * @param id is to find the product which will be deleted
     * @param userId property is used to determine if the user has authorisation to make changes in database
     * @return responseDto to inform front-end that process has been done successfully/ failed
     */
    @DeleteMapping("/{id}")
    ResponseEntity<ResponseDto> delete(@PathVariable long id, @RequestHeader String userId){
        ProductValidator.validateDeleteProduct(userId, HttpStatus.UNAUTHORIZED, "wrong");
        imageService.delete(id);
        productService.delete(id);
        ResponseDto responseDto = new ResponseDto("Product deleted.");
        responseDto.addInfo("productId", String.valueOf(id));
        return ResponseEntity.ok(responseDto);
    }

}
