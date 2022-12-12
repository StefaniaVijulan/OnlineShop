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
  isChecked: boolean= true;
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
        localStorage.setItem("id_user", JSON.stringify(data.user.id))
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
  loginDesigner(){
    console.log(this.loginRequest)
    this._login.loginDesigner(this.loginRequest).subscribe(
      data => {
        console.log("Response was received");
        console.log(data)
        if(!data)
          this.message="Utilizatorul cu aceste date de conectare nu exista!";
        else{
        localStorage.setItem('token',  JSON.stringify(data.jwt))
        localStorage.setItem('user', JSON.stringify(data.designer))
        localStorage.setItem("type", "designer")
        localStorage.setItem("id_user", JSON.stringify(data.designer.id))

          this._router.navigate(['/']).then(() => {
          window.location.href = window.location.href;
        });
        }
      },
      error => {
        console.log("Exception has occured");
        this.message="Adresa de email sau parola sunt incorecte! Mai incearca!";
    }
    )
  }
}
