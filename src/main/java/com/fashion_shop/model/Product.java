package com.fashion_shop.model;

import com.fashion_shop.model.commons.Descriptions;
import com.fashion_shop.model.commons.Image;
import com.fashion_shop.model.commons.Stock;
import com.fashion_shop.model.commons.enums.Currency;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Data
@ToString
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private Float price;

    @OneToOne(cascade = CascadeType.ALL)
    private Descriptions descriptions;

    @Enumerated(EnumType.STRING)
    private Currency currency;

    @OneToOne(cascade = CascadeType.ALL)
    private Stock stock;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Image> img;


    @Override
    public String toString() {
        return "" +
                "" + id +
                "" + name +
                "" + price +
                "" + descriptions +
                "" + currency +
                "" + stock +
                "" + img;
    }
}