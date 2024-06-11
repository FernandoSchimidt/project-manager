import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { User } from '../../models/User.interface';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class UserService {
  private readonly urlBase = 'http://localhost:8080/users';
  constructor(private http: HttpClient) {}

  getUsers(): Observable<User[]> {
    return this.http.get<User[]>(this.urlBase);
  }

  createUser(user: User): Observable<User> {
    return this.http.post<User>(this.urlBase, user);
  }
}
