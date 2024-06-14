package com.fernandoschimidt.project_manager.dtos;

public record RegisterRequestDTO (String name, String email, String password, String cpf) {
}