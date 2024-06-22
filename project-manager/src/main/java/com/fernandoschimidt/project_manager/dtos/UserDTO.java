package com.fernandoschimidt.project_manager.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class UserDTO {
    private Long id;
    private String name;
    private String email;
    private String cpf;
    private String role;
}