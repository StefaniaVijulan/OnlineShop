import {
  Component,
  OnInit
} from '@angular/core';
import {
  MatDialog
} from '@angular/material/dialog';
import {
  ProductStatusComponent
} from 'src/app/dialog/product-status/product-status.component';
import {
  DesignerService
} from 'src/app/services/designer.service';
import {
  Observable
} from "rxjs";
import { EditPriceComponent } from 'src/app/dialog/edit-price/edit-price.component';
import { EditPhotoComponent } from 'src/app/dialog/edit-photo/edit-photo.component';

@Component({
  selector: 'app-schite-asignate',
  templateUrl: './schite-asignate.component.html',
  styleUrls: ['./schite-asignate.component.css']
})
export class SchiteAsignateComponent implements OnInit {

  myProducts: any;
  price: any;
  constructor(private _designerService: DesignerService, private dialog: MatDialog) {}

  ngOnInit(): void {
    this.getMyDisgners();
  }

  getMyDisgners() {
    return this._designerService.getMyProduct()
      .subscribe((res) => {
        this.myProducts = res;
        console.log(this.myProducts)
      })
  }

  openDialogStatus(productId: any) {
    this._designerService.myProductId = productId;
    this.dialog.open(ProductStatusComponent, {
      width: '30%',
    })
  };
  editPrice(productId:any) {
    console.log("price")
    this._designerService.myProductId = productId;
    this.dialog.open(EditPriceComponent, {
      width: '30%',
    })
  }
  editImg(productId:any) {
    console.log("img")
    this._designerService.myProductId = productId;
    this.dialog.open(EditPhotoComponent, {
      width: '30%',
    })
  }
}
