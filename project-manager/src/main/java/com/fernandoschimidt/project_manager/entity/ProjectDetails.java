package com.fernandoschimidt.project_manager.entity;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ProjectDetails {
    private Long id;
    private String name;
    private LocalDate startDate;
    private LocalDate endDate;
    private int budgetedHours;
    private int totalHoursWorked;
    private int availableHours;
}
