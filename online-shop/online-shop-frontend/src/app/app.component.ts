import { Component } from '@angular/core';
import { User } from './models/user';
import {LoginService} from "./services/login.service";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'online-shop-frontend';
  display='none';
  user = new User();
  
  constructor(public _loginService: LoginService){

  }
  ngOnInit(){
    if(localStorage.getItem('type')=="user"){}
  }

  public signOut(){
    this._loginService.logoutUser()
  }

  openModal(){
          this.display='block';
       }
  onCloseHandled(){
            this.display='none';
         }
    
}
