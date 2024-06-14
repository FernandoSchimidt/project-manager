package com.fernandoschimidt.project_manager.controller;

import com.fernandoschimidt.project_manager.entity.Project;
import com.fernandoschimidt.project_manager.entity.ProjectDetails;
import com.fernandoschimidt.project_manager.entity.User;
import com.fernandoschimidt.project_manager.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/projects")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @PostMapping("/user")
    public ResponseEntity<Project> createProject(@RequestBody Project project) {
        Project newProject = projectService.saveProject(project);
        return ResponseEntity.ok(newProject);
    }

    @GetMapping("/{projectId}")
    public ResponseEntity<ProjectDetails> getProjectDetails(@PathVariable Long projectId) {
        ProjectDetails projectDetails = projectService.getProjectDetails(projectId);
        return ResponseEntity.ok(projectDetails);
    }

    @GetMapping
    public Page<Project> getProjects(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return projectService.getProjects(page, size);
    }

    @GetMapping("/user")
    public Page<Project> getProjectsByUserId(
            @RequestBody User user,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return projectService.getProjectsByUser(page, size, user);
    }

    @DeleteMapping("/{projectId}")
    public void deleteProject(@PathVariable Long projectId) {
        projectService.deleteProject(projectId);
    }
}
