import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { User, CreateUserRequest } from '../models/user.model';
import { AuthService } from './auth.service';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private apiUrl = `${environment.apiUrl}/users`;

  constructor(
    private http: HttpClient,
    private authService: AuthService
  ) {}

  private getHeaders(): HttpHeaders {
    const user = this.authService.getCurrentUser();
    const headers = new HttpHeaders({
      'Content-Type': 'application/json'
    });
    
    if (user) {
      headers.set('X-User-Id', user.id);
    }
    
    return headers;
  }

  getAllUsers(): Observable<User[]> {
    return this.http.get<User[]>(this.apiUrl, { headers: this.getHeaders() });
  }

  getUserById(id: string): Observable<User> {
    return this.http.get<User>(`${this.apiUrl}/${id}`, { headers: this.getHeaders() });
  }

  createUser(user: CreateUserRequest): Observable<User> {
    return this.http.post<User>(this.apiUrl, user, { headers: this.getHeaders() });
  }

  updateUser(id: string, user: CreateUserRequest): Observable<User> {
    return this.http.put<User>(`${this.apiUrl}/${id}`, user, { headers: this.getHeaders() });
  }

  deleteUser(id: string): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`, { headers: this.getHeaders() });
  }

  getUsersByRole(role: 'STUDENT' | 'INSTRUCTOR'): Observable<User[]> {
    return this.http.get<User[]>(`${this.apiUrl}?role=${role}`, { headers: this.getHeaders() });
  }
}