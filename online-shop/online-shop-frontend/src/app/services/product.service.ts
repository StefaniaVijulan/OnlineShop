import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Router} from "@angular/router";
import {environment} from "../../environments/environment";
import {Product} from "../models/product";

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  private baseUrl = environment.baseUrl;

  constructor(private _http: HttpClient, private _router: Router) {  }

  public getAllProducts(){
    return this._http.get<Product[]>(this.baseUrl + '/product/all');
  }

  public getProductById(id: number){
    return this._http.get<Product>(this.baseUrl + '/product/oneProduct/' + id);
  }

}
