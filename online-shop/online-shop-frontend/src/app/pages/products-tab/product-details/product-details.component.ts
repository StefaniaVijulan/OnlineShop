import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {Product} from "../../../models/product";
import {ProductService} from "../../../services/product.service";
import {ShopCartService} from "../../../services/shopCart.service";
import {User} from "../../../models/user";
import {ShopCart} from "../../../models/shopCart";

@Component({
  selector: 'app-product-details',
  templateUrl: './product-details.component.html',
  styleUrls: ['./product-details.component.css']
})
export class ProductDetailsComponent implements OnInit {

  idProduct: number = 0;
  currentProduct: Product = new Product();
  addedToCart: boolean = false;
  loggedUserStorage = localStorage.getItem('user');
  userTypeStorage = localStorage.getItem('type');
  userType: String = '';
  loggedUser: User = new User();
  productSaved: boolean = false;
  shopCartSaved: ShopCart = new ShopCart();

  constructor(private _router:Router, private _route: ActivatedRoute, private _productService: ProductService,
              private _shopCartService: ShopCartService) { }

  ngOnInit(): void {
    if(this.loggedUserStorage){
      this.loggedUser = JSON.parse(this.loggedUserStorage);
    }
    if(this.userTypeStorage){
      this.userType = this.userTypeStorage;
    }
    this.idProduct = Number(this._route.snapshot.url.toString().replace('productDetails,',''));
    this.getCurrentProduct();
  }

  getCurrentProduct(){
    return this._productService.getProductById(this.idProduct)
      .subscribe((res: Product) => {
        this.currentProduct = res;
        if(this.userType == 'user'){
          this._shopCartService.checkProductInShopCart(this.idProduct, this.loggedUser.id).subscribe((resp: boolean) =>
              this.addedToCart = resp
          )
          this._shopCartService.checkIfProductIsSaved(this.idProduct, this.loggedUser.id).subscribe((resp: ShopCart) => {
            if (resp == null) {
                  this.productSaved = false;
              }
              else{
                this.shopCartSaved = resp;
                this.productSaved = true;
            }
            }
          )
        }
      })
  }

  addToCart(){
    this._shopCartService.addProductToCart(this.currentProduct, false).subscribe();
    this.addedToCart = true;
  }

  saveOrDeleteProductFromSaves(){
    if(!this.productSaved){
      this._shopCartService.addProductToCart(this.currentProduct, true).subscribe((resp: ShopCart) =>{
        this.shopCartSaved = resp;
        this.productSaved = true;
    })}
    else{
      this._shopCartService.deleteProductInCart(this.shopCartSaved.id).subscribe();
      this.productSaved = false;
    }
  }
}
