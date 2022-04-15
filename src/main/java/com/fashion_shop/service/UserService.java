package com.fashion_shop.service;

import com.fashion_shop.model.User;
import org.apache.catalina.LifecycleState;

import java.util.List;

public interface UserService {
    User create(User user);

    User getById(String id);
}
