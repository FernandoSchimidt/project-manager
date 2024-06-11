import { Component } from '@angular/core';
import { Project } from '../../../models/Project.interface';
import { ProjectService } from '../../services/project.service';
import { DatePipe } from '@angular/common';
import { Router } from '@angular/router';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatMenuModule } from '@angular/material/menu';
import { MatButtonModule } from '@angular/material/button';
import { RouterLink } from '@angular/router';

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
  ],
  templateUrl: './project-list.component.html',
  styleUrl: './project-list.component.scss',
})
export class ProjectListComponent {
  projectsList: Project[] = [];

  constructor(private projectService: ProjectService, private router: Router) {
    this.findAllProjects();
  }

  findAllProjects() {
    this.projectService.getProjects().subscribe((res) => {
      this.projectsList = res;
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
      this.projectsList = res;
    });
  }
}
