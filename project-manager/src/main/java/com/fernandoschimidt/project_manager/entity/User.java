package com.fernandoschimidt.project_manager.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String cpf;

    @OneToMany(mappedBy = "user")
    @JsonIgnoreProperties("user")
    private List<Project> projects;
}
