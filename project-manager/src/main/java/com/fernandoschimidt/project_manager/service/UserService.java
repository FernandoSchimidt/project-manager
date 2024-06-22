package com.fernandoschimidt.project_manager.service;

import com.fernandoschimidt.project_manager.dtos.CreateUserDto;
import com.fernandoschimidt.project_manager.dtos.RegisterRequestDTO;
import com.fernandoschimidt.project_manager.entity.User;
import com.fernandoschimidt.project_manager.repository.UserRepository;

import jakarta.persistence.EntityNotFoundException;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    PasswordEncoder encoder;


    public User createUser(CreateUserDto createUserDto) {
        User newUser = new User();
                newUser.setName(createUserDto.name());
                newUser.setEmail(createUserDto.email());
                newUser.setCpf(createUserDto.cpf());
                newUser.setPassword(encoder.encode(createUserDto.password()));

        return userRepository.save(newUser);
    }


    public List<User> findAll() {
        return  userRepository.findAll();
    }

    public User findById(Long id) throws Exception {
        return userRepository.findById(id)
                .orElseThrow(() -> new Exception("user not found"));
    }
}