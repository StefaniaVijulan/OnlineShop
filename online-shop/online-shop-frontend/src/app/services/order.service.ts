import {Injectable} from "@angular/core";
import {environment} from "../../environments/environment";
import {HttpClient} from "@angular/common/http";
import {Router} from "@angular/router";
import {OrderProduct} from "../models/orderProduct";

@Injectable({
  providedIn: 'root'
})
export class OrderService {

  private baseUrl = environment.baseUrl;
  constructor(private _http: HttpClient, private _router: Router) {}

  public addOrder(order: OrderProduct){
    return this._http.post<OrderProduct>(this.baseUrl + '/orderProduct/save', order);
  }
}
