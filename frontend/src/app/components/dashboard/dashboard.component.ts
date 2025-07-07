import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router, RouterModule } from '@angular/router';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatCardModule } from '@angular/material/card';
import { MatMenuModule } from '@angular/material/menu';
import { AuthService, AuthUser } from '../../services/auth.service';
import { UserService } from '../../services/user.service';
import { GraduationService } from '../../services/graduation.service';
import { User } from '../../models/user.model';
import { GraduationHistory } from '../../models/graduation.model';

@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [
    CommonModule,
    RouterModule,
    MatToolbarModule,
    MatButtonModule,
    MatIconModule,
    MatCardModule,
    MatMenuModule
  ],
  templateUrl: './dashboard.component.html',
  styleUrl: './dashboard.component.scss'
})
export class DashboardComponent implements OnInit {
  currentUser: AuthUser | null = null;
  userDetails: User | null = null;
  recentGraduations: GraduationHistory[] = [];
  stats = {
    totalStudents: 0,
    recentGraduations: 0,
    myGraduations: 0
  };

  constructor(
    private authService: AuthService,
    private userService: UserService,
    private graduationService: GraduationService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.currentUser = this.authService.getCurrentUser();
    if (!this.currentUser) {
      this.router.navigate(['/login']);
      return;
    }

    this.loadUserDetails();
    this.loadStats();
    this.loadRecentGraduations();
  }

  private loadUserDetails(): void {
    if (this.currentUser) {
      this.userService.getUserById(this.currentUser.id).subscribe({
        next: (user) => {
          this.userDetails = user;
        },
        error: (error) => {
          console.error('Error loading user details:', error);
        }
      });
    }
  }

  private loadStats(): void {
    // Load total students
    this.userService.getUsersByRole('STUDENT').subscribe({
      next: (students) => {
        this.stats.totalStudents = students.length;
      },
      error: (error) => {
        console.error('Error loading students:', error);
      }
    });

    // Load user's graduations
    if (this.currentUser) {
      this.graduationService.getGraduationsByUser(this.currentUser.id).subscribe({
        next: (graduations) => {
          this.stats.myGraduations = graduations.length;
        },
        error: (error) => {
          console.error('Error loading user graduations:', error);
        }
      });
    }
  }

  private loadRecentGraduations(): void {
    this.graduationService.getAllGraduations().subscribe({
      next: (graduations) => {
        // Sort by date and take the 5 most recent
        this.recentGraduations = graduations
          .sort((a, b) => new Date(b.graduationDate).getTime() - new Date(a.graduationDate).getTime())
          .slice(0, 5);
        this.stats.recentGraduations = graduations.length;
      },
      error: (error) => {
        console.error('Error loading graduations:', error);
      }
    });
  }

  logout(): void {
    this.authService.logout();
    this.router.navigate(['/login']);
  }

  getCurrentBeltDisplay(): string {
    if (this.userDetails?.profile?.currentBelt) {
      return this.userDetails.profile.currentBelt.displayName;
    }
    return 'Faixa Branca';
  }

  isInstructor(): boolean {
    return this.authService.isInstructor();
  }
}