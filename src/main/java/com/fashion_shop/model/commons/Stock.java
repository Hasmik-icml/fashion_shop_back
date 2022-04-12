package com.fashion_shop.model.commons;

import lombok.Data;
import lombok.ToString;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@ToString
@Entity
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Boolean isAvailable;

    private Integer count;

//    public void setCount(Integer count) {
//        if (this.count == 0) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Product stock count is 0");
//        this.count = count;
//        if (this.count == 0) isAvailable = false;
//    }
}
