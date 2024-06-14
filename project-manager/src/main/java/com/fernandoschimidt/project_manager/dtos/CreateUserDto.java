package com.fernandoschimidt.project_manager.dtos;


import java.util.List;

public record CreateUserDto(
        String name,
        String email,
        String cpf,
        String password,
        List<Long> projectIds) {
}
