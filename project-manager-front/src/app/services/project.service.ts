import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Project } from '../../models/Project.interface';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class ProjectService {
  private readonly urlBase = 'http://localhost:8080/projects';
  constructor(private http: HttpClient) {}

  getProjects(): Observable<Project[]> {
    return this.http.get<Project[]>(this.urlBase);
  }
  findyById(id: number): Observable<Project> {
    return this.http.get<Project>(`${this.urlBase}/${id}`);
  }

  delete(id: number): Observable<any[]> {
    return this.http.delete<any[]>(`${this.urlBase}/${id}`);
  }
  createProject(project: Project): Observable<Project> {
    return this.http.post<Project>(`${this.urlBase}/user/1`, project);
  }
}
