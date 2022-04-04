package com.fashion_shop.service;

import com.fashion_shop.model.Product;
import com.fashion_shop.model.commons.Image;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


public interface ImageService {
    Product saveImagesToFolder(long productId, MultipartFile[] images);

    byte[] readAllByProductId(long productId, long imgId) throws IOException;

    Image update(long productId, MultipartFile[] images);

}
