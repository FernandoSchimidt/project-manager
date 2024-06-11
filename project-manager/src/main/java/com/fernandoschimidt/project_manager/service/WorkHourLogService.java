package com.fernandoschimidt.project_manager.service;

import com.fernandoschimidt.project_manager.entity.Project;
import com.fernandoschimidt.project_manager.entity.WorkHourLog;
import com.fernandoschimidt.project_manager.repository.ProjectRepository;
import com.fernandoschimidt.project_manager.repository.WorkHourLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkHourLogService {

    @Autowired
    private WorkHourLogRepository workHourLogRepository;

    @Autowired
    private ProjectRepository projectRepository;

    public WorkHourLog saveWorkHourLog(Long projectId, WorkHourLog workHourLog) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new IllegalArgumentException("Project not found."));
        WorkHourLog hourLog = new WorkHourLog();
        hourLog.setProject(project);
        hourLog.setHoursWorked(workHourLog.getHoursWorked());
        hourLog.setDate(workHourLog.getDate());

        if (workHourLog.getDate().isBefore(project.getStartDate()) || workHourLog.getDate().isAfter(project.getEndDate())) {
            throw new IllegalArgumentException("Log date must be within the project duration.");
        }
        if (workHourLog.getHoursWorked() < 0 || workHourLog.getHoursWorked() > 24) {
            throw new IllegalArgumentException("Hours worked must be between 0 and 24.");
        }
        return workHourLogRepository.save(hourLog);
    }

    public List<WorkHourLog> workHourLogs(Long projectId) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new IllegalArgumentException("Project not found."));

        return workHourLogRepository.findByProject(project);
    }

    public void deleteWorkHourLog(Long workId) {
        WorkHourLog workHourLog = workHourLogRepository.findById(workId)
                .orElseThrow(() -> new IllegalArgumentException("Worked Hour not found."));
        workHourLogRepository.deleteById(workId);
    }
}