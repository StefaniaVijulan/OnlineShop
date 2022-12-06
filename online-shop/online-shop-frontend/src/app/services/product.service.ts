import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Router} from "@angular/router";
import {environment} from "../../environments/environment";
import {Product} from "../models/product";
import {ShopCartService} from "./shopCart.service";

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  private baseUrl = environment.baseUrl;

  constructor(private _http: HttpClient, private _router: Router, private _shopCart:ShopCartService) {  }

  public getAllProducts(){
    return this._http.get<Product[]>(this.baseUrl + '/product/all');
  }

  public getProductById(id: number){
    console.log(id)
    return this._http.get<Product>(this.baseUrl + '/product/oneProduct/' + id);
  }

}
