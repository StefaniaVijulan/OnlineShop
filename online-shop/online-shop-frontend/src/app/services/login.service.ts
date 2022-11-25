import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { Router } from '@angular/router';
@Injectable({
  providedIn: 'root'
})
export class LoginService {

  constructor(private _http: HttpClient, private _router: Router) {  }

  public checkUserIsLogged(){
    // aici trebuie adaugata verificarea cu local storage daca user-ul e logat sau nu
    //return true;
    return false;
  }

  public checkIfUserIsClient(){
    // aici trebuie adaugata verificarea daca user-ul din local storage este client sau nu
    //return true;
    return false;
  }
}
