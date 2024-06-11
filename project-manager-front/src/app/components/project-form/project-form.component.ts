import { Component } from '@angular/core';

import { MatToolbarModule } from '@angular/material/toolbar';
import { MatIconModule } from '@angular/material/icon';
import { Router, RouterLink } from '@angular/router';
import { ProjectService } from '../../services/project.service';
import {
  FormBuilder,
  FormGroup,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';
import { Project } from '../../../models/Project.interface';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';

@Component({
  selector: 'app-project-form',
  standalone: true,
  imports: [
    MatToolbarModule,
    MatIconModule,
    RouterLink,
    ReactiveFormsModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
  ],
  templateUrl: './project-form.component.html',
  styleUrl: './project-form.component.scss',
})
export class ProjectFormComponent {
  projectForm!: FormGroup;
  constructor(
    private projectService: ProjectService,
    private fb: FormBuilder,
    private router: Router
  ) {
    this.projectForm = this.fb.group({
      name: ['', Validators.required],
      startDate: ['', Validators.required],
      endDate: ['', Validators.required],
      budgetedHours: [0, Validators.required],
    });
  }

  createProject() {
    if (this.projectForm.valid) {
      const formValue = this.projectForm.value;
      const newProject: Project = {
        ...formValue,
        startDate: this.formatDate(formValue.startDate),
        endDate: this.formatDate(formValue.endDate),
      };
      console.log(newProject);
      this.projectService.createProject(newProject).subscribe(
        (response) => {
          console.log('Project created successfully:', response);
          // Navegar para a página de detalhes ou lista de projetos após a criação
          this.router.navigate(['projects']);
        },
        (error) => {
          console.error('Error creating project:', error);
        }
      );
    }
  }

  private formatDate(date: string): string {
    const parsedDate = new Date(date);
    const year = parsedDate.getFullYear();
    const month = ('0' + (parsedDate.getMonth() + 1)).slice(-2);
    const day = ('0' + parsedDate.getDate()).slice(-2);
    return `${year}-${month}-${day}`;
  }
}
