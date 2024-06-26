import { Component, OnInit } from '@angular/core';
import { Project } from '../../../models/Project.interface';
import { ProjectService } from '../../services/project.service';
import { DatePipe } from '@angular/common';
import { Router } from '@angular/router';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatMenuModule } from '@angular/material/menu';
import { MatButtonModule } from '@angular/material/button';
import { RouterLink } from '@angular/router';
import { MatPaginatorModule } from '@angular/material/paginator';
import { MatTableModule } from '@angular/material/table';

import { MatIconModule } from '@angular/material/icon';

@Component({
  selector: 'app-project-list',
  standalone: true,
  imports: [
    DatePipe,
    MatIconModule,
    MatToolbarModule,
    MatMenuModule,
    RouterLink,
    MatIconModule,
    MatButtonModule,
    MatPaginatorModule,
    MatTableModule,
  ],
  templateUrl: './project-list.component.html',
  styleUrl: './project-list.component.scss',
})
export class ProjectListComponent implements OnInit {
  projects: Project[] = [];
  totalElements: number = 0;
  totalPages: number = 0;
  currentPage: number = 0;
  pageSize: number = 5;

  constructor(private projectService: ProjectService, private router: Router) {}
  ngOnInit(): void {
    this.findAllProjects(this.currentPage, this.pageSize);
    // this.getProjectsUserActive();
  }

  findAllProjects(page: number, size: number) {
    this.projectService.getProjects(page, size).subscribe((response) => {
      this.projects = response.content;
      this.totalElements = response.totalElements;
      this.totalPages = response.totalPages;
      this.currentPage = response.number;
    });
  }

  goToProjectDetails(id: number): void {
    this.router.navigate([`/projects/${id}`]);
  }

  confirmDelete(name: string, id: number) {
    if (confirm('Tem certeza que deseja deletar o projeto: ' + name)) {
      this.delete(id);
    }
  }

  delete(id: number) {
    this.projectService.delete(id).subscribe((res) => {
      this.projects = res;
    });
  }

  onPageChange(event: any): void {
    this.findAllProjects(event.pageIndex, event.pageSize);
  }

  // getProjectsUserActive() {
  //   this.projectService.getProjectsUserActive().subscribe((res) => {
  //     this.projects = res;
  //   });
  // }
}
