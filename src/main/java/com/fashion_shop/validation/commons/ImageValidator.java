package com.fashion_shop.validation.commons;

import com.fashion_shop.model.Product;
import com.fashion_shop.model.commons.Image;
import com.fashion_shop.validation.ValidationConstants;
import java.util.LinkedList;

public final class ImageValidator {
    public static void checkDefaultImage(Product product){
        if (product.getImg() == null){
            product.setImg(new LinkedList<>());
            product.getImg().add(new Image(ValidationConstants.DEFAULT_IMAGE_PATH));
        }else if(product.getImg().size() == 0){
            product.getImg().add(new Image(ValidationConstants.DEFAULT_IMAGE_PATH));
        }
    }
}
