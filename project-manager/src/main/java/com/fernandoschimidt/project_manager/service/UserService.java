package com.fernandoschimidt.project_manager.service;

import com.fernandoschimidt.project_manager.dtos.CreateUserDto;
import com.fernandoschimidt.project_manager.dtos.RegisterRequestDTO;
import com.fernandoschimidt.project_manager.entity.User;
import com.fernandoschimidt.project_manager.repository.UserRepository;

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

    @Transactional
    public List<User> findAll() {
        List<User> users = userRepository.findAll();
        for (User user : users) {
            user.getProjects().size(); // Inicializar a coleção projects
        }
        return users;
    }

}