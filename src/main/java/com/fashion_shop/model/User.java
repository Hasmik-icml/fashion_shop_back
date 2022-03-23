package com.fashion_shop.model;

import lombok.Data;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@ToString
@Entity
public class User {
    @Id
    private String id;

    private String name;

    private String email;

    private String picture;
}
