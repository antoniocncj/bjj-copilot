import { Routes } from '@angular/router';
import { LoginComponent } from './components/login/login.component';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { GraduationHistoryComponent } from './components/graduation-history/graduation-history.component';
import { AiChatComponent } from './components/ai-chat/ai-chat.component';

export const routes: Routes = [
  { path: '', redirectTo: '/login', pathMatch: 'full' },
  { path: 'login', component: LoginComponent },
  { path: 'dashboard', component: DashboardComponent },
  { path: 'graduation-history', component: GraduationHistoryComponent },
  { path: 'ai-chat', component: AiChatComponent },
  { path: '**', redirectTo: '/login' }
];
