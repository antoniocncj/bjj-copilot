import { Component, OnInit, ViewChild, ElementRef, AfterViewChecked } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Router, RouterModule } from '@angular/router';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatCardModule } from '@angular/material/card';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { MatSnackBar, MatSnackBarModule } from '@angular/material/snack-bar';
import { AuthService, AuthUser } from '../../services/auth.service';
import { ChatService } from '../../services/chat.service';
import { ChatMessage, ChatRequest } from '../../models/chat.model';

@Component({
  selector: 'app-ai-chat',
  standalone: true,
  imports: [
    CommonModule,
    FormsModule,
    RouterModule,
    MatToolbarModule,
    MatButtonModule,
    MatIconModule,
    MatCardModule,
    MatFormFieldModule,
    MatInputModule,
    MatProgressSpinnerModule,
    MatSnackBarModule
  ],
  templateUrl: './ai-chat.component.html',
  styleUrl: './ai-chat.component.scss'
})
export class AiChatComponent implements OnInit, AfterViewChecked {
  @ViewChild('messagesContainer') private messagesContainer!: ElementRef;
  
  currentUser: AuthUser | null = null;
  messages: ChatMessage[] = [];
  newMessage = '';
  isLoading = false;
  conversationId: string | null = null;

  constructor(
    private authService: AuthService,
    private chatService: ChatService,
    private router: Router,
    private snackBar: MatSnackBar
  ) {}

  ngOnInit(): void {
    this.currentUser = this.authService.getCurrentUser();
    if (!this.currentUser) {
      this.router.navigate(['/login']);
      return;
    }

    // Add welcome message
    this.addMessage({
      id: '1',
      content: 'Olá! Sou o BJJ Copilot IA. Posso ajudar você com dúvidas sobre técnicas de Brazilian Jiu-Jitsu, posições, estratégias e muito mais. Como posso ajudá-lo hoje?',
      isUser: false,
      timestamp: Date.now()
    });
  }

  ngAfterViewChecked(): void {
    this.scrollToBottom();
  }

  sendMessage(): void {
    if (!this.newMessage.trim() || this.isLoading) {
      return;
    }

    const userMessage: ChatMessage = {
      id: Date.now().toString(),
      content: this.newMessage.trim(),
      isUser: true,
      timestamp: Date.now()
    };

    this.addMessage(userMessage);
    
    const chatRequest: ChatRequest = {
      message: this.newMessage.trim(),
      conversationId: this.conversationId || undefined
    };

    this.newMessage = '';
    this.isLoading = true;

    this.chatService.sendMessage(chatRequest).subscribe({
      next: (response) => {
        this.conversationId = response.conversationId;
        
        const aiMessage: ChatMessage = {
          id: response.timestamp.toString(),
          content: response.response,
          isUser: false,
          timestamp: response.timestamp
        };

        this.addMessage(aiMessage);
        this.isLoading = false;
      },
      error: (error) => {
        console.error('Error sending message:', error);
        this.snackBar.open('Erro ao enviar mensagem. Tente novamente.', 'Fechar', {
          duration: 3000
        });
        
        const errorMessage: ChatMessage = {
          id: Date.now().toString(),
          content: 'Desculpe, ocorreu um erro ao processar sua mensagem. Tente novamente.',
          isUser: false,
          timestamp: Date.now()
        };
        
        this.addMessage(errorMessage);
        this.isLoading = false;
      }
    });
  }

  private addMessage(message: ChatMessage): void {
    this.messages.push(message);
  }

  private scrollToBottom(): void {
    try {
      if (this.messagesContainer) {
        this.messagesContainer.nativeElement.scrollTop = this.messagesContainer.nativeElement.scrollHeight;
      }
    } catch (err) {
      console.error('Could not scroll to bottom:', err);
    }
  }

  goBack(): void {
    this.router.navigate(['/dashboard']);
  }

  clearChat(): void {
    this.messages = [];
    this.conversationId = null;
    this.ngOnInit(); // Re-add welcome message
  }

  onKeyPress(event: KeyboardEvent): void {
    if (event.key === 'Enter' && !event.shiftKey) {
      event.preventDefault();
      this.sendMessage();
    }
  }
}