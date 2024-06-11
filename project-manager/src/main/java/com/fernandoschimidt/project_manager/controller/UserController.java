package com.fernandoschimidt.project_manager.controller;


import com.fernandoschimidt.project_manager.entity.User;
import com.fernandoschimidt.project_manager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User newUser = userService.saveUser(user);
        return ResponseEntity.ok(newUser);
    }
    @GetMapping
    public ResponseEntity<List<User>> findAllUsers(){
        List<User> users = userService.findAll();
        return  new ResponseEntity<>(users, HttpStatus.OK);
    }
}