import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {Product} from "../../../models/product";

@Component({
  selector: 'app-product-details',
  templateUrl: './product-details.component.html',
  styleUrls: ['./product-details.component.css']
})
export class ProductDetailsComponent implements OnInit {

  idProduct: number = 0;
  currentProduct: Product = new Product();

  constructor(private _router:Router, private _route: ActivatedRoute) { }

  ngOnInit(): void {
    this._route.queryParams.subscribe(params => {
      this.idProduct = JSON.parse(params["productId"]);
    });

    this.getCurrentProduct();

  }

  getCurrentProduct(){
    //o sa trebuiasca modificat
    this.currentProduct = { idProduct: this.idProduct, sketching: "",finalProduct: "", productName:"Sacou office",price:600,description:"Sacou de culoare neagră, potrivit pentru o zi la birou. Materialul este unul subțire și lejer."}
  }

}
