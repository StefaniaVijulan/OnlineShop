import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Designer } from 'src/app/models/designer';
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
  designer = new Designer();
  isChecked: boolean = true;
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
        this._router.navigate(['/inregistrare']);
      },
      error =>{
        console.log("exception occured");
        this.msg = error.error;
      }
    )
  }

  registerDesigner(){
    this.designer.email = this.user1.email
    this.designer.firstNameDesigner = this.user1.firstName
    this.designer.lastNameDesigner = this.user1.lastName
    this.designer.passwordDesigner = this.user1.password
    this._register.registerDesigner(this.designer).subscribe(
      data =>{
        console.log("response recive");
        console.log(data)
       this.msg="Register successful";
        this._router.navigate(['/inregistrare']);
      },
      error =>{
        console.log("exception occured");
        this.msg = error.error;
      }
    )
  }
}
