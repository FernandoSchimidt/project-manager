package com.fernandoschimidt.project_manager.controller;


import com.fernandoschimidt.project_manager.entity.WorkHourLog;
import com.fernandoschimidt.project_manager.service.WorkHourLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/work-hour-logs")
public class WorkHourLogController {

    @Autowired
    private WorkHourLogService workHourLogService;

    @PostMapping("/{projectId}")
    public ResponseEntity<WorkHourLog> logHours(@PathVariable Long projectId, @RequestBody WorkHourLog workHourLog) {
        WorkHourLog newWorkHourLog = workHourLogService.saveWorkHourLog(projectId, workHourLog);
        return ResponseEntity.ok(newWorkHourLog);
    }

    @GetMapping("/{projectId}")
    public ResponseEntity<List<WorkHourLog>> getAllLogHours(@PathVariable Long projectId) {
        List<WorkHourLog> workHourLogs = workHourLogService.workHourLogs(projectId);
        return ResponseEntity.ok(workHourLogs);
    }

    @DeleteMapping("/{workId}")
    public void deleteWorkHourLog(@PathVariable Long workId) {
        workHourLogService.deleteWorkHourLog(workId);
    }
}