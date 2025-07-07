import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable, of } from 'rxjs';

export interface AuthUser {
  id: string;
  name: string;
  email: string;
  role: 'STUDENT' | 'INSTRUCTOR';
}

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private currentUserSubject = new BehaviorSubject<AuthUser | null>(null);
  public currentUser$ = this.currentUserSubject.asObservable();

  constructor() {
    // Check if there's a saved user in localStorage
    const savedUser = localStorage.getItem('currentUser');
    if (savedUser) {
      this.currentUserSubject.next(JSON.parse(savedUser));
    }
  }

  login(email: string, password: string): Observable<AuthUser | null> {
    // Mock login for now - in real app this would call the backend
    if (email && password) {
      const mockUser: AuthUser = {
        id: '550e8400-e29b-41d4-a716-446655440000',
        name: email.split('@')[0],
        email: email,
        role: email.includes('instructor') ? 'INSTRUCTOR' : 'STUDENT'
      };
      
      localStorage.setItem('currentUser', JSON.stringify(mockUser));
      this.currentUserSubject.next(mockUser);
      return of(mockUser);
    }
    
    return of(null);
  }

  logout(): void {
    localStorage.removeItem('currentUser');
    this.currentUserSubject.next(null);
  }

  getCurrentUser(): AuthUser | null {
    return this.currentUserSubject.value;
  }

  isLoggedIn(): boolean {
    return this.currentUserSubject.value !== null;
  }

  isInstructor(): boolean {
    const user = this.currentUserSubject.value;
    return user?.role === 'INSTRUCTOR';
  }
}