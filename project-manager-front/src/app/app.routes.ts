import { Routes } from '@angular/router';
import { ProjectListComponent } from './components/project-list/project-list.component';
import { UserListComponent } from './components/user-list/user-list.component';
import { ProjectFormComponent } from './components/project-form/project-form.component';
import { UserFormComponent } from './components/user-form/user-form.component';
import { ProjectDetailsComponent } from './components/project-details/project-details.component';

export const routes: Routes = [
  { path: '', component: ProjectListComponent },
  { path: 'projects', component: ProjectListComponent },
  { path: 'projects-form', component: ProjectFormComponent },
  { path: 'projects/:id', component: ProjectDetailsComponent },
  { path: 'users', component: UserListComponent },
  { path: 'users-form', component: UserFormComponent },
];
