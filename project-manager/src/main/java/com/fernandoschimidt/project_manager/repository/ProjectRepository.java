package com.fernandoschimidt.project_manager.repository;

import com.fernandoschimidt.project_manager.entity.Project;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface ProjectRepository extends PagingAndSortingRepository<Project, Long> {
    Optional<Project> findById(Long projectId);

    void deleteById(Long projectId);

    Project save(Project project);
}
