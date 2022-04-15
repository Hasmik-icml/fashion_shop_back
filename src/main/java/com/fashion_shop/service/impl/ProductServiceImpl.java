package com.fashion_shop.service.impl;

import com.fashion_shop.model.Product;
import com.fashion_shop.repository.ProductRepository;
import com.fashion_shop.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    /***
     *
     * @return all data from DB, if there is not any data will return empty List.
     */
    @Override
    public List<Product> getAll() {
        List<Product> all = productRepository.findAll();
        Collections.reverse(all);
//        Collections.sort(all, new Comparator<Product>() {
//            @Override
//            public int compare(Product o1, Product o2) {
////                return o2.getDate() - o1.getDate();
//            }
//        });
        return all;
    }

    /***
     *
     * @return the product with provided ID
     */
    @Override
    public Product getById(Long id) {
        return productRepository.findById(id).orElseThrow(()->{
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "product with id:" + id + "  not found in database");
        });
    }

    /***
     *
     * @param product the product that would be added in DB
     * @return new product which has added
     */
    @Override
    public Product create(Product product) {
        return productRepository.save(product);
    }

    /***
     *
     * @param id is related to product which need to update
     * @param product is changed data
     * @returns just updated product
     */
    @Override
    @Transactional
    public Product update(Product product, long id) {
        Product dbProduct = productRepository.findById(id).orElseThrow(()->{
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "product with id:" + id + "  not found in database");
        });
        dbProduct.setName(product.getName());
        dbProduct.setPrice(product.getPrice());
        dbProduct.setStock(product.getStock());
//        dbProduct.setImg(product.getImg());//???????????
        dbProduct.setCurrency(product.getCurrency());
        dbProduct.setDescription(product.getDescription());
        return  dbProduct;
    }

    /***
     *
     * @param id find the product with provided id and deletes both the image folder
     *           corresponding to the product and the product
     */
    @Override
    public void delete(long id) {
        productRepository.deleteById(id);
    }
}
