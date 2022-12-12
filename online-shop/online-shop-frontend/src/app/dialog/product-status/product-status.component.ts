import { Component, OnInit } from '@angular/core';
import { MatDialogRef } from '@angular/material/dialog';
import { DesignerService } from 'src/app/services/designer.service';

@Component({
  selector: 'app-product-status',
  templateUrl: './product-status.component.html',
  styleUrls: ['./product-status.component.css']
})
export class ProductStatusComponent implements OnInit {
  acceptat=false;
  refuzat=false;
  in_pregatire=false;
  nepreluat=false;
  finalizat=false;
  constructor(private designer: DesignerService, private dialogref: MatDialogRef <ProductStatusComponent>) { }

  ngOnInit(): void {
  }

  editStatus(){
    if(this.acceptat == true)
    {
      this.designer.editStatus("acceptat").subscribe((res)=>{
        console.log(res)
        window.location.reload();
      this.dialogref.close("add");
      })
    }
    else  if(this.refuzat == true)
    {
      this.designer.editStatus("refuzat").subscribe((res)=>{
        console.log(res)
        window.location.reload();
      this.dialogref.close("add");
      })
    }
    if(this.in_pregatire == true)
    {
      this.designer.editStatus("in_pregatire").subscribe((res)=>{
        console.log(res)
        window.location.reload();
      this.dialogref.close("add");
      })
    }
    else if(this.nepreluat == true)
    {
      this.designer.editStatus("nepreluat").subscribe((res)=>{
        console.log(res)
        window.location.reload();
      this.dialogref.close("add");
      })
    }
    else  if(this.finalizat == true)
    {
      this.designer.editStatus("finalizat").subscribe((res)=>{
        console.log(res)
        window.location.reload();
      this.dialogref.close("add");
      })
    }
    
  }
}
