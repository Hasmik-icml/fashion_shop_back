package com.fashion_shop.model.commons;

import lombok.Data;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@ToString
@Entity
public class Descriptions {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String comment;
}
