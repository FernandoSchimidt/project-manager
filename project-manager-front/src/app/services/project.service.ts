import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Project } from '../../models/Project.interface';
import { Observable } from 'rxjs';
import { PaginatedProjects } from '../../models/PaginatedProjects .interface';


@Injectable({
  providedIn: 'root',
})
export class ProjectService {
  private readonly urlBase = 'http://localhost:8080/projects';
  constructor(private http: HttpClient) {}

  /**
   * Retrieves the list of all projects with pagination.
   * @param page - The page number.
   * @param size - The page size.
   * @returns An Observable of Project array.
   */
  getProjects(page: number, size: number): Observable<PaginatedProjects> {
    // Crie um objeto HttpParams e defina os parâmetros de página e tamanho
    const params = new HttpParams()
      .set('page', page.toString())
      .set('size', size.toString());

    // Passe os parâmetros na solicitação GET
    return this.http.get<PaginatedProjects>(this.urlBase, { params });
  }

  findyById(id: number): Observable<Project> {
    return this.http.get<Project>(`${this.urlBase}/${id}`);
  }

  delete(id: number): Observable<any[]> {
    return this.http.delete<any[]>(`${this.urlBase}/${id}`);
  }
  createProject(project: Project,userId:string): Observable<Project> {
    return this.http.post<Project>(`${this.urlBase}/user/${userId}`, project);
  }
}
