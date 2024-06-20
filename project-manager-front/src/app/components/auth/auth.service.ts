import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { LoginResponse } from './LoginResponse';
import { LoginRequest } from './LoginRequest';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private readonly urlBase = 'http://localhost:8080/auth/';

  constructor(private http: HttpClient) {}

  login(loginRequest: LoginRequest): Observable<LoginRequest> {
    return this.http.post<LoginRequest>(`${this.urlBase}login`, loginRequest);
  }
}
