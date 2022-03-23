package com.fashion_shop.controller;

import com.fashion_shop.model.User;
import com.fashion_shop.repository.UserRepository;
import com.fashion_shop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserRepository userRepository;//??? ինչի userService chi

    @GetMapping
    List<User> getAll(){
        return  userRepository.findAll();
    }
}
