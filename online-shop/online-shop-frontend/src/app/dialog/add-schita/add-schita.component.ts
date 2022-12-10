import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Designer } from 'src/app/models/designer';
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
  constructor(private _formBuilder: FormBuilder,private productService: ProductService) { }

  ngOnInit(): void {
    this.firstFormGroup = this._formBuilder.group({
      productName: ['', Validators.required],
      description: ['', Validators.required],
      id:['', Validators.required],
      status: "nepreluat"
    });
    this.selectedDesigner = new FormControl();
    this.getDesigners()
  }
  addSchita(){
    console.log(this.firstFormGroup.value)
  }
  getDesigners(){
    return this.productService.allDesigner()
      .subscribe((res: Designer[]) => {
        this.allDesginersList = res;
        console.log(this.allDesginersList)
      })
  }
}
