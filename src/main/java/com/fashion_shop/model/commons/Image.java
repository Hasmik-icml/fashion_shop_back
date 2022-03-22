package com.fashion_shop.model.commons;

import com.fashion_shop.model.Product;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Data
@RequiredArgsConstructor
@ToString
@Entity
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String imagePath;

//    ??????????
    public Image(String s) {
        this.imagePath = s;
    }
}
