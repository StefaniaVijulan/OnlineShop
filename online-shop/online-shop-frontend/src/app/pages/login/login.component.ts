import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {


  // userr = new User();
  message = '';
  constructor() { }

  ngOnInit(): void {
  }

  // loginUser(){
  //   this._service.loginUserFromRemote(this.userr).subscribe(
  //     data => {
  //       console.log("Response was received");
  //       localStorage.setItem('token',  JSON.stringify(data))
  //       this._router.navigate(['/dashboard'])
  //     },
  //     error => {
  //       console.log("Exception has occured");
  //       this.message="Wrong email or password, please Retry!";
  //   }     
  //   )
  // }

}
