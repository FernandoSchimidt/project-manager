package com.fernandoschimidt.project_manager.repository;

import com.fernandoschimidt.project_manager.entity.Project;
import com.fernandoschimidt.project_manager.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface ProjectRepository extends PagingAndSortingRepository<Project, Long> {
    Optional<Project> findById(Long projectId);
    Page<Project> findByUser(Long id, Pageable pageable);

    void deleteById(Long projectId);

    Project save(Project project);
}
