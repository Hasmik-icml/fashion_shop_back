package com.fashion_shop.validation;

import com.fashion_shop.model.User;
import com.fashion_shop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import javax.annotation.PostConstruct;

@Component
public final class UserValidator {
    private static UserService userService;

    @Autowired
    private UserService userServiceT;

    @PostConstruct
    public void init(){
        UserValidator.userService = userServiceT;
    }

    public static void checkUserAuthorized(String user_id, HttpStatus status, String message) {
        try{
            if (user_id == null || userService.getById(user_id) == null) {
                throw new ResponseStatusException(status, message);
            }
        }catch (ResponseStatusException statusException){
            throw new ResponseStatusException(status,message);
        }
    }

    public static void checkUserSignUp(User user, HttpStatus status, String message) {
        if (user.getEmail() == null || user.getEmail().length() == 0 ||
                user.getName() == null || user.getName().length() == 0 ||
                user.getPicture() == null || user.getPicture().length() == 0 ||
                user.getId() == null || user.getId().length() == 0) {
            throw new ResponseStatusException(status, message);
        }

    }
}
