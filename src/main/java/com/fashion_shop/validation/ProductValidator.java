package com.fashion_shop.validation;

import com.fashion_shop.model.Product;
import com.fashion_shop.validation.commons.DescriptionValidator;
import com.fashion_shop.validation.commons.ImageValidator;
import com.fashion_shop.validation.commons.StockValidator;

public final class ProductValidator {
    public static boolean validateUpdateProduct(Product product) {
        ImageValidator.checkDefaultImage(product);

        if (product.getName().length() == 0 ||
                product.getPrice() < 0 ||
                !StockValidator.validateStock(product.getStock()) ||
                        !DescriptionValidator.validateDescription(product.getDescriptions())) {
            return false;
        }

        return true;
    }
    public static boolean validateCreateProduct(Product product){
        System.out.println(product.getStock());
        System.out.println(product.getDescriptions());
        return validateUpdateProduct(product);
    }

}
