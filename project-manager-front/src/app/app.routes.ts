import { Routes } from '@angular/router';
import { ProjectListComponent } from './components/project-list/project-list.component';
import { UserListComponent } from './components/user-list/user-list.component';
import { ProjectFormComponent } from './components/project-form/project-form.component';
import { UserFormComponent } from './components/user-form/user-form.component';
import { ProjectDetailsComponent } from './components/project-details/project-details.component';
import { LoginComponent } from './components/auth/login/login.component';
import { RegisterComponent } from './components/auth/register/register.component';
import { authGuard } from './auth.guard';

export const routes: Routes = [
  { path: '', redirectTo: '/login', pathMatch: 'full' },
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  {
    path: 'projects',
    component: ProjectListComponent,
    canActivate: [authGuard],
  },
  {
    path: 'projects-form',
    component: ProjectFormComponent,
    canActivate: [authGuard],
  },
  {
    path: 'projects/:id',
    component: ProjectDetailsComponent,
    canActivate: [authGuard],
  },
  { path: 'users', component: UserListComponent, canActivate: [authGuard] },
  {
    path: 'users-form',
    component: UserFormComponent,
    canActivate: [authGuard],
  },
];
