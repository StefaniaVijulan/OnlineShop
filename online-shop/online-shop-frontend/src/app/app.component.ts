import { Component } from '@angular/core';
import { User } from './models/user';
import {LoginService} from "./services/login.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'online-shop-frontend';
  display='none';
  user = new User();
  isClient: boolean = false;
  isDesigner: boolean = false;

  constructor(public _loginService: LoginService, private _router:Router){

  }
  ngOnInit(){
    if(localStorage.getItem('type')=="user"){
      this.isClient = true;
    } else
      if(localStorage.getItem('type')=="designer"){
      this.isDesigner = true;
    }
  }

  public signOut(){
    this._loginService.logoutUser();
    this.isClient = false;
  }

  openModal(){
          this.display='block';
       }
  onCloseHandled(){
            this.display='none';
         }

}
