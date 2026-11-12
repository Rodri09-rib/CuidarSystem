import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';

@Injectable({providedIn : 'root'})
export class UserService {

  private apiUrl: string = 'http://localhost:8080/user';

  constructor(private http : HttpClient) {}

  createUser(user : UserRequestDto): Observable<any> {
    return this.http.post(this.apiUrl, user);
  }

  login(credentials: LoginRequestDto): Observable<any> {
    return this.http.post(this.apiUrl + '/login', credentials);
  }

}

export interface UserRequestDto {
  name:String;
  email:String;
  password:String;
}

export interface LoginRequestDto {
  email:String;
    password:String;
}


