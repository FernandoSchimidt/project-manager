package com.fernandoschimidt.project_manager.repository;

import com.fernandoschimidt.project_manager.dtos.UserDTO;
import com.fernandoschimidt.project_manager.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

}
