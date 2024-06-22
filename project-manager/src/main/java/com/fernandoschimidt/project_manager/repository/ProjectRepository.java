package com.fernandoschimidt.project_manager.repository;

import com.fernandoschimidt.project_manager.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Long> {

}
