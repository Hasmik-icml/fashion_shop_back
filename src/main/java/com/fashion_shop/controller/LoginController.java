package com.fashion_shop.controller;

import com.fashion_shop.model.User;
import com.fashion_shop.service.UserService;
import com.fashion_shop.validation.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping
public class LoginController {

    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    ResponseEntity<User> signup(@RequestBody User user){
        if (!UserValidator.checkUserSignUp(user)){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "user data is invalid to signUp" );
        }
    return ResponseEntity.ok(userService.create(user));
}
}
