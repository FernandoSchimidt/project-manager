package com.fernandoschimidt.project_manager.service;

import com.fernandoschimidt.project_manager.entity.WorkHourLog;
import com.fernandoschimidt.project_manager.entity.Project;
import com.fernandoschimidt.project_manager.entity.ProjectDetails;
import com.fernandoschimidt.project_manager.entity.User;
import com.fernandoschimidt.project_manager.exception.ProjectNotFoundException;
import com.fernandoschimidt.project_manager.repository.ProjectRepository;
import com.fernandoschimidt.project_manager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectService {
    @Autowired
    private ProjectRepository repository;

    @Autowired
    private UserRepository userRepository;

    public Page<Project> findAll(int page, int size) {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        String username = authentication.getName();
//
//        User user = userRepository.findByEmail(username)
//                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
//        System.out.println("Usuario: "+user);

        Pageable pageable = PageRequest.of(page, size);
        return repository.findAll(pageable);
    }



    public Project create(Project project) {
        User user = userRepository.findById(project.getUser().getId())
                .orElseThrow(() -> new IllegalArgumentException("User not found."));

        if (project.getStartDate().isAfter(project.getEndDate())) {
            throw new IllegalArgumentException("Start date must be before or equal to end date.");
        }
        if (project.getBudgetedHours() <= 0) {
            throw new IllegalArgumentException("Budgeted hours must be greater than zero.");
        }

        project.setUser(user);
        return repository.save(project);
    }

    public ProjectDetails getProjectDetails(Long projectId) {
        Project project = repository.findById(projectId)
                .orElseThrow(() -> new IllegalArgumentException("Project not found."));

        // Calcula as horas consumidas
        int totalHoursWorked = project.getWorkHourLogs().stream()
                .mapToInt(WorkHourLog::getHoursWorked)
                .sum();

        // Calcula as horas disponíveis
        int availableHours = project.getBudgetedHours() - totalHoursWorked;

        // Cria e retorna os detalhes do projeto
        ProjectDetails projectDetails = new ProjectDetails();
        projectDetails.setId(project.getId());
        projectDetails.setName(project.getName());
        projectDetails.setStartDate(project.getStartDate());
        projectDetails.setEndDate(project.getEndDate());
        projectDetails.setBudgetedHours(project.getBudgetedHours());
        projectDetails.setTotalHoursWorked(totalHoursWorked);
        projectDetails.setAvailableHours(availableHours);

        return projectDetails;
    }


    public Project findById(Long idProject) {
        return repository.findById(idProject)
                .orElseThrow(() -> new ProjectNotFoundException("Project not found with id: " + idProject));
    }

    public void deleteProject(Long projectId) {
        Optional<Project> project = repository.findById(projectId);

        if (project.isPresent()) {
            repository.deleteById(projectId);
        } else {
            throw new IllegalArgumentException("Project not found.");
        }

    }
}
