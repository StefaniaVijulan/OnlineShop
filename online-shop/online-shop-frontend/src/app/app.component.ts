import { Component } from '@angular/core';
import {LoginService} from "./services/login.service";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'online-shop-frontend';

  constructor(public _loginService: LoginService){

  }

  public signOut(){
    //aici o sa vina stergerea din local storage a utilizatorului
  }

}
