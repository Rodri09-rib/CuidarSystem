import { Component, signal } from '@angular/core';
import {CommonModule} from '@angular/common';
import {FormsModule} from '@angular/forms';
import {UserRequestDto, UserService} from './UserService';
import {RouterModule} from '@angular/router';


@Component({
  imports:[CommonModule, FormsModule,RouterModule],
  selector: 'app-root',
  styleUrl: './app.scss',
  templateUrl: './app.html',
})
export class App {
  protected readonly title = signal('cuidar-front');

  currentMode : 'login' | 'register' = 'login';

  newUser : UserRequestDto = {
    name: '',
    email: '',
    password: ''
  };

  constructor(private userService : UserService) {}

  changeMode(mode: 'login' | "register") {
    this.currentMode = mode;
  }

  registerUser() {
    this.userService.createUser(this.newUser).subscribe({
      next: (answer) => {
        alert('Account created!');
        this.changeMode('login');
      },
      error: (erro) => {
        console.error('Error trying to create account!');
        alert('Error trying to register account!');
      }
    });
  }
}


