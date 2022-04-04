package com.fashion_shop.repository;

import com.fashion_shop.model.commons.Image;
import org.springframework.data.jpa.repository.JpaRepository;



public interface ImageRepository extends JpaRepository<Image, Long>{
}
