import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {Product} from "../../../models/product";
import {ProductService} from "../../../services/product.service";

@Component({
  selector: 'app-product-details',
  templateUrl: './product-details.component.html',
  styleUrls: ['./product-details.component.css']
})
export class ProductDetailsComponent implements OnInit {

  idProduct: number = 0;
  currentProduct: Product = new Product();

  constructor(private _router:Router, private _route: ActivatedRoute, private _productService: ProductService) { }

  ngOnInit(): void {
    this.idProduct = Number(this._route.snapshot.url.toString().replace('productDetails,',''));
    this.getCurrentProduct();

  }

  getCurrentProduct(){
    return this._productService.getProductById(this.idProduct)
      .subscribe((res: Product) => {
        this.currentProduct = res;
      })
  }

}
