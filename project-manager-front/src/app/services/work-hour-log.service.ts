import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { WorkHourLog } from '../../models/WorkHourLog .interface';
import { Observable } from 'rxjs';
import { WorkHourRequest } from '../../models/WorkHourRequest';

@Injectable({
  providedIn: 'root',
})
export class WorkHourLogService {
  private readonly urlBase = 'http://localhost:8080/work-hour-logs';
  constructor(private http: HttpClient) {}

  getWorkHourLogs(id: number): Observable<WorkHourLog[]> {
    return this.http.get<WorkHourLog[]>(`${this.urlBase}/${id}`);
  }
  workHourLog(req: WorkHourRequest, projectId: number) {
    return this.http.post<any>(`${this.urlBase}/${projectId}`, req);
  }
  deleteWorkHourLog(id: number) {
    return this.http.delete(`${this.urlBase}/${id}`);
  }
}
