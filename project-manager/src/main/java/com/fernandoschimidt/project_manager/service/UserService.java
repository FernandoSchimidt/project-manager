package com.fernandoschimidt.project_manager.service;

import com.fernandoschimidt.project_manager.entity.User;
import com.fernandoschimidt.project_manager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User saveUser(User user) {
        // Add any business logic for user
        return userRepository.save(user);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }
}