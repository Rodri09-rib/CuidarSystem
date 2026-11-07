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

}

export interface UserRequestDto {
  name:String;
  email:String;
  password:String;
}
