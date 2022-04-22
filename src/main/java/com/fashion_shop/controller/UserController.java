package com.fashion_shop.controller;

import com.fashion_shop.model.User;
import com.fashion_shop.model.dto.ResponseDto;
import com.fashion_shop.repository.UserRepository;
import com.fashion_shop.service.UserService;
import com.fashion_shop.validation.UserValidator;
import com.fashion_shop.validation.ValidationConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    @Autowired
    private UserService userService;

    /***
     *
     * @return returns the list of all registerd users
     */
    @GetMapping
    List<User> getAll(){
        return  userService.getAll();
    }
    @GetMapping("userId")
    ResponseEntity<ResponseDto> isUserExists(@RequestHeader String userId){
        UserValidator.checkUserAuthorized(userId, HttpStatus.UNAUTHORIZED, ValidationConstants.UNAUTHORIZED_ERROR);
        ResponseDto responseDto = new ResponseDto("User exists.");
        responseDto.addInfo("exists", String.valueOf(userService.isExists(userId)));
        return ResponseEntity.ok(responseDto);
    }
}
