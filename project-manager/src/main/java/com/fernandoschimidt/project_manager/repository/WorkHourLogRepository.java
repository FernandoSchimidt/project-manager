package com.fernandoschimidt.project_manager.repository;

import com.fernandoschimidt.project_manager.entity.Project;
import com.fernandoschimidt.project_manager.entity.WorkHourLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WorkHourLogRepository extends JpaRepository<WorkHourLog, Long> {

    List<WorkHourLog> findByProject(Project project);
}