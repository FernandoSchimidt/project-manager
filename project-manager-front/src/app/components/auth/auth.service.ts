import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, tap } from 'rxjs';
import { LoginResponse } from './LoginResponse';
import { LoginRequest } from './LoginRequest';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private readonly urlBase = 'http://localhost:8080/auth';

  constructor(private http: HttpClient) {}

  login(loginRequest: LoginRequest): Observable<LoginResponse> {
    return this.http
      .post<LoginResponse>(`${this.urlBase}/login`, loginRequest)
      .pipe(
        tap((response: LoginResponse) => {
          this.saveToken(response.token);
        })
      );
  }
  private saveToken(token: string): void {
    localStorage.setItem('token', token);
  }

  getToken(): string | null {
    return localStorage.getItem('token');
  }

  logout(): void {
    localStorage.removeItem('token');
  }

  isLoggedIn(): boolean {
    // Lógica para verificar se o usuário está autenticado
    // Exemplo simples usando localStorage
    return localStorage.getItem('token') !== null;
  }
}
