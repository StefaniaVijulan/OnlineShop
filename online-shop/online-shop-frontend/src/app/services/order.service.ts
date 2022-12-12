import {Injectable} from "@angular/core";
import {environment} from "../../environments/environment";
import {HttpClient} from "@angular/common/http";
import {Router} from "@angular/router";
import {OrderProduct} from "../models/orderProduct";
import {Comments} from "../models/comments";

@Injectable({
  providedIn: 'root'
})
export class OrderService {

  private baseUrl = environment.baseUrl;
  constructor(private _http: HttpClient, private _router: Router) {}

  public addOrder(order: OrderProduct){
    return this._http.post<OrderProduct>(this.baseUrl + '/orderProduct/save', order);
  }

  public getProductsClient(clientId: number){
    return this._http.get<OrderProduct[]>(this.baseUrl + '/orderProduct/getProductsClient/' + clientId);
  }

  public getProductsDesigner(designerId: number){
    return this._http.get<OrderProduct[]>(this.baseUrl + '/orderProduct/getProductsDesigner/' + designerId);
  }

  public finalizeOrder(orderProduct: OrderProduct, finalized: boolean){
    return this._http.patch<OrderProduct>(this.baseUrl + '/orderProduct/finalizeOrder/' + finalized, orderProduct);
  }
}
