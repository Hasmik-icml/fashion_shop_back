package com.fashion_shop.service.impl;

import com.fashion_shop.model.User;
import com.fashion_shop.repository.UserRepository;
import com.fashion_shop.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl  implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User create(User user) {
        return userRepository.save(user);
    }

//    @Override
//    public User getById(String id) {
//        return userRepository.findById(id).orElseThrow(()->{
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"wrong");
//        });
//        return userRepository.getById(id);
//    }

    @Override
    public List<User> getAll() {
        return null;
    }

    @Override
    public void delete(String id) {

    }
}
