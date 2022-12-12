import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { environment } from 'src/environments/environment';
import { Designer } from '../models/designer';
import { User } from '../models/user';

@Injectable({
  providedIn: 'root'
})
export class RegisterService {
  private baseUrl = environment.baseUrl;
  private publicHttpHeaders = {
    headers: new HttpHeaders({
      'content-type': 'application/json'
    })
  };
  constructor(private _http: HttpClient, private _router: Router) { }

  public registerUser(user: User){
    console.log("user is ", user)
    return this._http.post<any>(this.baseUrl + "/register", user)
  }
  public registerDesigner(designer: Designer){
    console.log("designer is ", designer)
    return this._http.post<any>(this.baseUrl + "/designer/registerDesigner", designer)
  }

}
