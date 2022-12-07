import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Router} from "@angular/router";
import {environment} from "../../environments/environment";
import {Product} from "../models/product";
import {ShopCart} from "../models/shopCart";

@Injectable({
  providedIn: 'root'
})
export class ShopCartService {

  private baseUrl = environment.baseUrl;
  newShopCart: ShopCart = new ShopCart();
  loggedUser = localStorage.getItem('user');

  constructor(private _http: HttpClient, private _router: Router) {  }

  public addProductToCart(product: Product, saved:boolean){
    this.newShopCart.product = product;
    if(this.loggedUser){
      this.newShopCart.client = JSON.parse(this.loggedUser);
    }
    this.newShopCart.savedProduct = saved;
    this.newShopCart.quantity = 1;

    return this._http.post<ShopCart>(this.baseUrl + '/shopCart/addProductInCart', this.newShopCart);
  }

  public updateQuantity(shopCart: ShopCart, newQuantity: number){
    return this._http.patch<ShopCart>(this.baseUrl + '/shopCart/updateQuantity/' + newQuantity, shopCart);
  }

  public getAllProductsFromShopCart(){
    let clientId;
    if (this.loggedUser) {
      clientId = JSON.parse(this.loggedUser).id;
    }
    return this._http.get<ShopCart[]>(this.baseUrl + '/shopCart/allProducts/' + clientId);
  }

  public getAllProductsSaved(){
    let clientId;
    if (this.loggedUser) {
      clientId = JSON.parse(this.loggedUser).id;
    }
    return this._http.get<ShopCart[]>(this.baseUrl + '/shopCart/savedProducts/' + clientId);
  }


  public checkProductInShopCart(productId: number, clientId: number){

    return this._http.get<boolean>(this.baseUrl + '/shopCart/checkProductInShopCart/' + productId + '/' + clientId);

  }

  public checkIfProductIsSaved(productId: number, clientId: number){

    return this._http.get<ShopCart>(this.baseUrl + '/shopCart/checkIfProductIsSaved/' + productId + '/' + clientId);

  }

  public deleteProductInCart(shopCartId: number){
    return this._http.delete(this.baseUrl + '/shopCart/deleteProductInCart/' + shopCartId);

  }

}
