import { Component, OnInit } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Router} from "@angular/router";
import {ShopCartService} from "../../services/shopCart.service";
import {ShopCart} from "../../models/shopCart";

@Component({
  selector: 'app-shop-cart',
  templateUrl: './shop-cart.component.html',
  styleUrls: ['./shop-cart.component.css']
})
export class ShopCartComponent implements OnInit {

  productsInShopCart: ShopCart[] = [];
  totalPrice: number = 0;

  constructor(private _httpClient: HttpClient, private _router:Router, private _shopCartService: ShopCartService) { }

  ngOnInit(): void {
    this.getProductsFromCart();
  }

  getProductsFromCart(){
    return this._shopCartService.getAllProductsFromShopCart()
      .subscribe((res: ShopCart[]) => {
        this.productsInShopCart = res;
        this.getTotalPrice();
      })
  }

  updateQuantity(shopCart:ShopCart, updatedQuantity: number){
    return this._shopCartService.updateQuantity(shopCart, shopCart.quantity + updatedQuantity)
      .subscribe(() => {
        this.getProductsFromCart();
      })
  }

  deleteProductInCart(shopCart:ShopCart){
    this._shopCartService.deleteProductInCart(shopCart.id)
      .subscribe(() => {
        this.getProductsFromCart();
      })
  }

  getTotalPrice(){
    this.totalPrice = 0;
    for(let i = 0; i<this.productsInShopCart.length;i++){
      this.totalPrice += this.productsInShopCart[i].product.price * this.productsInShopCart[i].quantity;
    }
  }

  goToProductShow(productId:number){
    this._router.navigate(["/productDetails/" + productId]);
  }

  goToOrder(){
    this._router.navigate(["/order"]);
  }
}
