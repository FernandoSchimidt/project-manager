package com.fernandoschimidt.project_manager.repository;
import com.fernandoschimidt.project_manager.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.fernandoschimidt.project_manager.entity.Project;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    Page<Project> findByUser(User user, Pageable pageable);

    List<Project> findByUserId(Long id);
}
