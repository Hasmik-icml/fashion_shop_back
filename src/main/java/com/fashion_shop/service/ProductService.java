package com.fashion_shop.service;

import com.fashion_shop.model.Product;
import org.springframework.stereotype.Service;

import java.util.List;


public interface ProductService {
    List<Product> getAll();

    Product getById(Long id);

    List<Product> getByAnyText(String anytext);

    Product create(Product product);

    Product update(Product product, long id);

    void delete(long id);

}
