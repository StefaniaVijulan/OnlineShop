import { Component, OnInit } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Router} from "@angular/router";
import {ShopCartService} from "../../services/shopCart.service";
import {ShopCart} from "../../models/shopCart";

@Component({
  selector: 'app-saved-products',
  templateUrl: './saved-products.component.html',
  styleUrls: ['./saved-products.component.css']
})
export class SavedProductsComponent implements OnInit {

  productsSaved: ShopCart[] = [];

  constructor(private _httpClient: HttpClient, private _router:Router, private _shopCartService: ShopCartService) { }

  ngOnInit(): void {
    this.getSavedProducts();
  }

  goToProductShow(productId:number){
    this._router.navigate(["/productDetails/" + productId]);
  }

  getSavedProducts(){
    return this._shopCartService.getAllProductsSaved()
      .subscribe((res: ShopCart[]) => {
        this.productsSaved = res;
      })
  }
}
