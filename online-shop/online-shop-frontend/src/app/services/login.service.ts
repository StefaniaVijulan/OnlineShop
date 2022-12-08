import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { Router } from '@angular/router';
import { User } from '../models/user';
import { environment } from 'src/environments/environment';
import { LoginRequest } from '../models/loginRequest';
@Injectable({
  providedIn: 'root'
})
export class LoginService {

  private baseUrl = environment.baseUrl;
  constructor(private _http: HttpClient, private _router: Router) {  }
  loggedIn(){
    return !!localStorage.getItem('token')
  }

  logoutUser(){
    localStorage.removeItem('token')
    localStorage.removeItem('user')
    localStorage.removeItem('type')
    this._router.navigate(['/'])
  }

  getToken(){
    return localStorage.getItem('token')
  }

  public loginUser(loginRequest: LoginRequest):Observable<any>{
    return this._http.post<any>(this.baseUrl + "/login", loginRequest)
  }
  public loginDesigner(loginRequest: LoginRequest):Observable<any>{
      console.log(loginRequest)
      return this._http.post<any>(this.baseUrl + "/designer/loginDesigner", loginRequest)
}


}
