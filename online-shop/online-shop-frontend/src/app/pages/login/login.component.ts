import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { LoginRequest } from 'src/app/models/loginRequest';
import { LoginService } from 'src/app/services/login.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {


  loginRequest = new LoginRequest()
  message = '';
  constructor(private _login: LoginService,private _router: Router) { }

  ngOnInit(): void {
  }

  loginUser(){
    this._login.loginUser(this.loginRequest).subscribe(
      data => {
        console.log("Response was received");
        console.log(data)
        localStorage.setItem('token',  JSON.stringify(data.jwt))
        localStorage.setItem('user', JSON.stringify(data.user))
        localStorage.setItem("type", "user")
        this._router.navigate(['/']).then(() => {
          window.location.href = window.location.href;
        });
      },
      error => {
        console.log("Exception has occured");
        this.message="Wrong email or password, please Retry!";
    }
    )
  }

}
