package com.fashion_shop.model.commons;

import com.fashion_shop.model.Product;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

@Data
@ToString
@Entity
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String imagePath;

    @ManyToOne
    private Product product;

}
