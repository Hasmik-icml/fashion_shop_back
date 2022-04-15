package com.fashion_shop.service;

import com.fashion_shop.model.Product;
import java.util.List;

public interface ProductService {
    List<Product> getAll();

    Product getById(Long id);

    Product create(Product product);

    Product update(Product product, long id);

    void delete(long id);
}
