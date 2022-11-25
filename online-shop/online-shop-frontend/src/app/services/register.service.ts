import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { environment } from 'src/environments/environment';

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

  // public registerUserFromRemote(user: User):Observable<any>{
  //   return this._http.post<any>("http://localhost:8080/register", user)
  // }

}
