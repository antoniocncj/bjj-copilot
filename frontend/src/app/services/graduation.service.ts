import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { GraduationHistory, CreateGraduationRequest } from '../models/graduation.model';
import { AuthService } from './auth.service';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class GraduationService {
  private apiUrl = `${environment.apiUrl}/graduations`;

  constructor(
    private http: HttpClient,
    private authService: AuthService
  ) {}

  private getHeaders(): HttpHeaders {
    const user = this.authService.getCurrentUser();
    let headers = new HttpHeaders({
      'Content-Type': 'application/json'
    });
    
    if (user) {
      headers = headers.set('X-User-Id', user.id);
      if (user.role === 'INSTRUCTOR') {
        headers = headers.set('X-Instructor-Id', user.id);
      }
    }
    
    return headers;
  }

  getAllGraduations(): Observable<GraduationHistory[]> {
    return this.http.get<GraduationHistory[]>(this.apiUrl, { headers: this.getHeaders() });
  }

  getGraduationsByUser(userId: string): Observable<GraduationHistory[]> {
    return this.http.get<GraduationHistory[]>(`${this.apiUrl}/user/${userId}`, { headers: this.getHeaders() });
  }

  getGraduationsByInstructor(instructorId: string): Observable<GraduationHistory[]> {
    return this.http.get<GraduationHistory[]>(`${this.apiUrl}/instructor/${instructorId}`, { headers: this.getHeaders() });
  }

  createGraduation(graduation: CreateGraduationRequest): Observable<GraduationHistory> {
    return this.http.post<GraduationHistory>(this.apiUrl, graduation, { headers: this.getHeaders() });
  }
}