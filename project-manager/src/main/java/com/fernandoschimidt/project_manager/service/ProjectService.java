package com.fernandoschimidt.project_manager.service;

import com.fernandoschimidt.project_manager.entity.Project;
import com.fernandoschimidt.project_manager.entity.ProjectDetails;
import com.fernandoschimidt.project_manager.entity.User;
import com.fernandoschimidt.project_manager.entity.WorkHourLog;
import com.fernandoschimidt.project_manager.repository.ProjectRepository;
import com.fernandoschimidt.project_manager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private UserRepository userRepository;

    public Project saveProject(Long userId, Project project) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found."));

        if (project.getStartDate().isAfter(project.getEndDate())) {
            throw new IllegalArgumentException("Start date must be before or equal to end date.");
        }
        if (project.getBudgetedHours() <= 0) {
            throw new IllegalArgumentException("Budgeted hours must be greater than zero.");
        }

        project.setUser(user);
        return projectRepository.save(project);
    }

    public ProjectDetails getProjectDetails(Long projectId) {
        Project project = projectRepository.findById(projectId)
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

    public List<Project> getAllProjects() {
        List<Project> projects = projectRepository.findAll();
        return projects;
    }


    public List<Project> deleteProject(Long projectId) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new IllegalArgumentException("Project not found."));

        projectRepository.deleteById(projectId);

        return projectRepository.findAll();
    }
}
