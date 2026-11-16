import { Component, signal } from '@angular/core';
import {CommonModule} from '@angular/common';
import {FormsModule} from '@angular/forms';
import {LoginRequestDto, UserRequestDto, UserService} from './UserService';
import {RouterModule} from '@angular/router';


@Component({
  imports: [CommonModule, FormsModule, RouterModule],
  selector: 'app-root',
  styleUrl: './app.scss',
  templateUrl: './app.html',
})
export class App {
  protected readonly title = signal('cuidar-front');

  currentMode : 'login' | 'register' = 'login';

  constructor(private userService : UserService) {}

  newUser : UserRequestDto = {
    name: '',
    email: '',
    password: ''
  }

  loginData : LoginRequestDto = {
    email: '',
    password: ''
  }

  changeMode(mode: 'login' | 'register') {
    this.currentMode = mode;
  }

  loginUser() {
    this.userService.login(this.newUser).subscribe({
      next: (answer) => {
        alert('Login success!');
        console.log('Login success!');
      },
      error: (erro) => {
        console.error('Email or password incorrect! ', erro);
        alert('Email or password incorrect!');
      }
    });
  }

  registerUser() {
    this.userService.createUser(this.newUser).subscribe({
      next : (answer) => {
        alert('User created!');
        this.changeMode('login');
      },
      error : (erro) => {
        console.error('Error trying to register user!', erro);
        alert('Error trying to register user');
      }
    });
  }

}




