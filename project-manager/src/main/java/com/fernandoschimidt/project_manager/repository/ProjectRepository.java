package com.fernandoschimidt.project_manager.repository;
import com.fernandoschimidt.project_manager.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.fernandoschimidt.project_manager.entity.Project;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    Page<Project> findByUser(User user, Pageable pageable);

    List<Project> findByUserId(Long id);


    @Query("SELECT p FROM Project p WHERE p.user.email = :email")
    Page<Project> findByUserEmail(@Param("email") String email, Pageable pageable);
}
