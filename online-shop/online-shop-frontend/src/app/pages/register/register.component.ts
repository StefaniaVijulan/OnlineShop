import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { User } from 'src/app/models/user';
import { RegisterService } from 'src/app/services/register.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  public myForm!:FormGroup;
  constructor(private _register: RegisterService,private _router: Router, private formBuilder:FormBuilder) { }
  user1 =new User();
  msg='';
  ngOnInit(): void {
    this.myForm = this.formBuilder.group({
      firstName:["", Validators.required],
      lastName:["", Validators.required],
      email:["",[Validators.required, Validators.email]],
      password:["", [Validators.required, Validators.minLength(5)]]
    })
  }

  register(){
    this._register.registerUser(this.user1).subscribe(
      data =>{
        console.log("response recive");
        console.log(data)
       this.msg="Register successful";
        this._router.navigate(['/login']);
      },
      error =>{
        console.log("exception occured");
        this.msg = error.error;
      }
    )
  }
  
}
