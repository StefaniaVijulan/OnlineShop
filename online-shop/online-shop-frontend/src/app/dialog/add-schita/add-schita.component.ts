import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef } from '@angular/material/dialog';
import { concat } from 'rxjs';
import { ChangeImg } from 'src/app/models/changeImg';
import { Designer } from 'src/app/models/designer';
import { Product } from 'src/app/models/product';
import { ProductService } from 'src/app/services/product.service';
import {User} from "../../models/user";

@Component({
  selector: 'app-add-schita',
  templateUrl: './add-schita.component.html',
  styleUrls: ['./add-schita.component.css']
})
export class AddSchitaComponent implements OnInit {
  firstFormGroup!: FormGroup;
  msg = '';
  form: any ={};
  allDesginersList: any;
  selectedDesigner:any;
  product = new Product();
  loggedUserStorage = localStorage.getItem('user');
  userTypeStorage = localStorage.getItem('type');
  userType: String = '';
  loggedUser: User = new User();
  changeImg: ChangeImg | undefined;
  constructor( private dialogref: MatDialogRef <AddSchitaComponent>,
    private _formBuilder: FormBuilder,private productService: ProductService) { }

  ngOnInit(): void {
    this.firstFormGroup = this._formBuilder.group({
      productName: ['', Validators.required],
      description: ['', Validators.required],
      status: "nepreluat"
    });

    if(this.userTypeStorage){
      this.userType = this.userTypeStorage;
      if(this.userType == 'user' && this.loggedUserStorage){
        this.loggedUser = JSON.parse(this.loggedUserStorage);
      }
    }

    this.selectedDesigner = new FormControl();
    this.getDesigners()
  }
  addSchita(){
   
  }
  getDesigners(){
    return this.productService.allDesigner()
      .subscribe((res: Designer[]) => {
        this.allDesginersList = res;
        console.log(this.allDesginersList)
      })
  }

  onSubmit(){
    this.product.description = this.firstFormGroup.value.description
    this.product.productName = this.firstFormGroup.value.productName
    this.product.status = this.firstFormGroup.value.status
    for(let i=0; i<this.allDesginersList.length; i++){
      if(this.allDesginersList[i].id == this.selectedDesigner.value){
        this.product.designer = this.allDesginersList[i]
      }
    }
    this.product.user = this.loggedUser;
    this.product.sketching = this.form.avatar;
    return this.productService.saveProduct(this.product).subscribe((res =>{
      window.location.reload();
      this.dialogref.close("add");
    }))
    }
    uploadAvatar($event: any){
      this.form.avatar = $event;
    }
}
