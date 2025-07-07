import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router, RouterModule } from '@angular/router';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatCardModule } from '@angular/material/card';
import { MatTableModule } from '@angular/material/table';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { MatSnackBar, MatSnackBarModule } from '@angular/material/snack-bar';
import { AuthService, AuthUser } from '../../services/auth.service';
import { GraduationService } from '../../services/graduation.service';
import { GraduationHistory } from '../../models/graduation.model';

@Component({
  selector: 'app-graduation-history',
  standalone: true,
  imports: [
    CommonModule,
    RouterModule,
    MatToolbarModule,
    MatButtonModule,
    MatIconModule,
    MatCardModule,
    MatTableModule,
    MatProgressSpinnerModule,
    MatSnackBarModule
  ],
  templateUrl: './graduation-history.component.html',
  styleUrl: './graduation-history.component.scss'
})
export class GraduationHistoryComponent implements OnInit {
  currentUser: AuthUser | null = null;
  graduations: GraduationHistory[] = [];
  displayedColumns: string[] = ['userName', 'fromBelt', 'toBelt', 'graduationDate', 'instructorName'];
  isLoading = true;

  constructor(
    private authService: AuthService,
    private graduationService: GraduationService,
    private router: Router,
    private snackBar: MatSnackBar
  ) {}

  ngOnInit(): void {
    this.currentUser = this.authService.getCurrentUser();
    if (!this.currentUser) {
      this.router.navigate(['/login']);
      return;
    }

    // Adjust columns based on user role
    if (!this.isInstructor()) {
      this.displayedColumns = ['fromBelt', 'toBelt', 'graduationDate', 'instructorName'];
    }

    this.loadGraduations();
  }

  private loadGraduations(): void {
    this.isLoading = true;
    
    if (this.isInstructor()) {
      // Instructors see all graduations
      this.graduationService.getAllGraduations().subscribe({
        next: (graduations) => {
          this.graduations = graduations.sort((a, b) => 
            new Date(b.graduationDate).getTime() - new Date(a.graduationDate).getTime()
          );
          this.isLoading = false;
        },
        error: (error) => {
          console.error('Error loading graduations:', error);
          this.snackBar.open('Erro ao carregar graduações', 'Fechar', {
            duration: 3000
          });
          this.isLoading = false;
        }
      });
    } else {
      // Students see only their graduations
      this.graduationService.getGraduationsByUser(this.currentUser!.id).subscribe({
        next: (graduations) => {
          this.graduations = graduations.sort((a, b) => 
            new Date(b.graduationDate).getTime() - new Date(a.graduationDate).getTime()
          );
          this.isLoading = false;
        },
        error: (error) => {
          console.error('Error loading user graduations:', error);
          this.snackBar.open('Erro ao carregar suas graduações', 'Fechar', {
            duration: 3000
          });
          this.isLoading = false;
        }
      });
    }
  }

  isInstructor(): boolean {
    return this.authService.isInstructor();
  }

  goBack(): void {
    this.router.navigate(['/dashboard']);
  }

  getBeltColor(belt: any): string {
    return belt.colorCode || '#000000';
  }
}