package com.fernandoschimidt.project_manager.controller;

import com.fernandoschimidt.project_manager.entity.Project;
import com.fernandoschimidt.project_manager.entity.ProjectDetails;
import com.fernandoschimidt.project_manager.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/projects")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @GetMapping
    public ResponseEntity<Page<Project>> findAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Page<Project> projects = projectService.findAll(page, size);

        return ResponseEntity.ok(projects);
    }

    @GetMapping("{idProject}")
    public ResponseEntity<ProjectDetails> findlById(@PathVariable(name = "idProject") Long idProject) {
        ProjectDetails projectDetails = projectService.getProjectDetails(idProject);
        return ResponseEntity.ok(projectDetails);
    }

    @PostMapping
    public ResponseEntity<Project> create(@RequestBody Project project) {
        Project newProject = this.projectService.create(project);
        return ResponseEntity.ok(project);
    }

    @DeleteMapping("/{projectId}")
    public void deleteProject(@PathVariable Long projectId) {
        projectService.deleteProject(projectId);
    }
}
