package com.fernandoschimidt.project_manager.repository;

import com.fernandoschimidt.project_manager.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
