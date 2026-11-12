import { Component, signal } from '@angular/core';
import {CommonModule} from '@angular/common';
import {FormsModule} from '@angular/forms';
import {LoginRequestDto, UserRequestDto, UserService} from './UserService';
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

  loginData: LoginRequestDto = {
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

  loginUser() {
    this.userService.login(this.loginData).subscribe({
      next: (answer) => {
        alert(('Login success!'));
        console.log('Date receive', answer);
      },
      error : (erro) => {
        console.error('Error trying to make login: ', erro);
        alert('E-mail or password incorrect.');
      }
    });
  }
}


