import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { ProductStatusComponent } from 'src/app/dialog/product-status/product-status.component';
import { DesignerService } from 'src/app/services/designer.service';

@Component({
  selector: 'app-schite-asignate',
  templateUrl: './schite-asignate.component.html',
  styleUrls: ['./schite-asignate.component.css']
})
export class SchiteAsignateComponent implements OnInit {

  myProducts: any;
  constructor(private _designerService: DesignerService,private dialog: MatDialog) { }

  ngOnInit(): void {
    this.getMyDisgners();
  }

  getMyDisgners(){
    return this._designerService.getMyProduct()
      .subscribe((res) => {
        this.myProducts = res;
        console.log(this.myProducts)
      })
  }

  openDialogStatus(productId:any){ 
    this._designerService.myProductId = productId;
    this.dialog.open(ProductStatusComponent,{ 
      width: '20%',   
    })
 };

}
