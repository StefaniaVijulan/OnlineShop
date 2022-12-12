import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef } from '@angular/material/dialog';
import { concat } from 'rxjs';
import { Designer } from 'src/app/models/designer';
import { Product } from 'src/app/models/product';
import { ProductService } from 'src/app/services/product.service';

@Component({
  selector: 'app-add-schita',
  templateUrl: './add-schita.component.html',
  styleUrls: ['./add-schita.component.css']
})
export class AddSchitaComponent implements OnInit {
  firstFormGroup!: FormGroup;
  msg = ''
  allDesginersList: any;
  selectedDesigner:any;
  product = new Product();
  constructor( private dialogref: MatDialogRef <AddSchitaComponent>,
    private _formBuilder: FormBuilder,private productService: ProductService) { }

  ngOnInit(): void {
    this.firstFormGroup = this._formBuilder.group({
      productName: ['', Validators.required],
      description: ['', Validators.required],
      status: "nepreluat"
    });
    this.selectedDesigner = new FormControl();
    this.getDesigners()
  }
  addSchita(){
    this.product.description = this.firstFormGroup.value.description
    this.product.productName = this.firstFormGroup.value.productName
    this.product.status = this.firstFormGroup.value.status
    for(let i=0; i<this.allDesginersList.length; i++){
      if(this.allDesginersList[i].id == this.selectedDesigner.value){
        this.product.designer = this.allDesginersList[i]
      }
    }
    // console.log(this.product)
    return this.productService.saveProduct(this.product).subscribe((res =>{
      window.location.reload();
      this.dialogref.close("add");
    }))
  }
  getDesigners(){
    return this.productService.allDesigner()
      .subscribe((res: Designer[]) => {
        this.allDesginersList = res;
        console.log(this.allDesginersList)
      })
  }
}
