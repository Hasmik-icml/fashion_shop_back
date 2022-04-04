package com.fashion_shop.controller;

import com.fashion_shop.model.Product;
import com.fashion_shop.model.commons.Image;
import com.fashion_shop.model.dto.ResponseDto;
import com.fashion_shop.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("api/v1/image")
public class ImageController {

    @Autowired
    private ImageService imageService;

    @PostMapping("/add/{product_id}")
    void addImage(@PathVariable("product_id") long productId, @RequestParam("image") MultipartFile[] multipartFile) {

        imageService.saveImagesToFolder(productId, multipartFile);
    }

    @GetMapping(value = "/get/{product_id}/{img_id}")
    ResponseEntity<byte[]> getImagesByProductId(@PathVariable("product_id") long productId, @PathVariable("img_id") long img_id) throws IOException {

        return ResponseEntity
                .ok()
                .contentType(MediaType.IMAGE_PNG)
                .body(imageService.readAllByProductId(productId,img_id));
    }

    @PutMapping("/update/{product_id}")
    ResponseEntity<ResponseDto> update(@PathVariable("product_id") long productId, @RequestParam("image") MultipartFile[] images ){
        Image updated = imageService.update(productId, images);
        System.out.println(updated);
        ResponseDto responseDto = new ResponseDto("Image updated.");
        responseDto.addInfo("productId", String.valueOf(productId));
        return ResponseEntity.ok(responseDto);

    }

}
