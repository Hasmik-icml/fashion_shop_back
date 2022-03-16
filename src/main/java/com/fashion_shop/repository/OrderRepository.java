package com.fashion_shop.repository;

import com.fashion_shop.model.OrderList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderList, Long> {
}
