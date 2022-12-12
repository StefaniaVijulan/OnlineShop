import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef } from '@angular/material/dialog';
import { DesignerService } from 'src/app/services/designer.service';
import { ProductService } from 'src/app/services/product.service';

@Component({
  selector: 'app-edit-price',
  templateUrl: './edit-price.component.html',
  styleUrls: ['./edit-price.component.css']
})
export class EditPriceComponent implements OnInit {
  firstFormGroup!: FormGroup;

  constructor(private dialogref: MatDialogRef <EditPriceComponent>,
    private _formBuilder: FormBuilder,private designer: DesignerService) { }

  ngOnInit(): void {
    this.firstFormGroup = this._formBuilder.group({
      price: [0, Validators.required],
     
    });

  }
  savePrice(){
    console.log(this.firstFormGroup.value.price)
    this.designer.editPrice(this.firstFormGroup.value.price).subscribe((res)=>{
      console.log(res)
      window.location.reload();
      this.dialogref.close("edit");
    })
  }
}
