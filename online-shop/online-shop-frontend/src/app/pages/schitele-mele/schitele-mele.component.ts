import { Component, OnInit } from '@angular/core';
import { ProductService } from 'src/app/services/product.service';

@Component({
  selector: 'app-schitele-mele',
  templateUrl: './schitele-mele.component.html',
  styleUrls: ['./schitele-mele.component.css']
})
export class SchiteleMeleComponent implements OnInit {

  myProducts: any;
  constructor(private _product: ProductService) { }

  ngOnInit(): void {
    this.getMyOwnProduct();
  }

  getMyOwnProduct(){
    return this._product.getMyProduct()
      .subscribe((res) => {
        this.myProducts = res;
        console.log(this.myProducts)
      })
  }

}
