package com.fernandoschimidt.project_manager.controller;

import com.fernandoschimidt.project_manager.dtos.CreateUserDto;
import com.fernandoschimidt.project_manager.dtos.LoginRequestDTO;
import com.fernandoschimidt.project_manager.dtos.RegisterRequestDTO;
import com.fernandoschimidt.project_manager.dtos.ResponseDTO;
import com.fernandoschimidt.project_manager.entity.User;
import com.fernandoschimidt.project_manager.infra.securrity.TokenService;
import com.fernandoschimidt.project_manager.repository.UserRepository;
import com.fernandoschimidt.project_manager.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;
    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginRequestDTO body){
        User user = this.repository.findByEmail(body.email()).orElseThrow(() -> new RuntimeException("User not found"));
        if(passwordEncoder.matches(body.password(), user.getPassword())) {
            String token = this.tokenService.generateToken(user);
            return ResponseEntity.ok(new ResponseDTO(user.getName(), token));
        }
        return ResponseEntity.badRequest().build();
    }


    @PostMapping("/register")
    public ResponseEntity register(@RequestBody CreateUserDto body){
        Optional<User> user = this.repository.findByEmail(body.email());

        if(user.isEmpty()) {
            User newUser = new User();
            newUser.setPassword(body.password());
            newUser.setEmail(body.email());
            newUser.setName(body.name());
            newUser.setCpf(body.cpf());

            this.userService.createUser(body);

            String token = this.tokenService.generateToken(newUser);
            return ResponseEntity.ok(new ResponseDTO(newUser.getName(), token));
        }
        return ResponseEntity.badRequest().build();
    }
}