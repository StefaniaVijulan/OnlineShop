import { Component, OnInit } from '@angular/core';
import {OrderProduct} from "../../models/orderProduct";
import {HttpClient} from "@angular/common/http";
import {NavigationEnd, NavigationStart, Router} from "@angular/router";
import {OrderService} from "../../services/order.service";
import {User} from "../../models/user";
import {ShopCart} from "../../models/shopCart";
import {Designer} from "../../models/designer";
import {Comments} from "../../models/comments";

@Component({
  selector: 'app-placed-orders',
  templateUrl: './placed-orders.component.html',
  styleUrls: ['./placed-orders.component.css']
})
export class PlacedOrdersComponent implements OnInit {

  ordersPlaced: OrderProduct[] = [];
  loggedUserStorage = localStorage.getItem('user');
  loggedUserClient: User = new User();
  loggedUserDesigner: Designer = new Designer();
  userTypeStorage = localStorage.getItem('type');
  userType: String = '';

  constructor(private _httpClient: HttpClient, private _router:Router, private _orderService:OrderService) { }

  ngOnInit(): void {
    if(this.userTypeStorage){
      this.userType = this.userTypeStorage;
      if(this.loggedUserStorage){
        if(this.userType == 'user'){
          this.loggedUserClient = JSON.parse(this.loggedUserStorage);
        }
        else{
          this.loggedUserDesigner = JSON.parse(this.loggedUserStorage);
        }
      }
    }
    this.getOrdersPlaced();
  }

  getOrdersPlaced(){
    if(this.userType == 'user'){
      this._orderService.getProductsClient(this.loggedUserClient.id).subscribe((res: OrderProduct[]) => {
         this.ordersPlaced = res;
       })
    }
    else {
      this._orderService.getProductsDesigner(this.loggedUserDesigner.id).subscribe((res: OrderProduct[]) => {
        this.ordersPlaced = res;
      })
    }
  }

  goToProductShow(productId:number){
    this._router.navigate(["/productDetails/" + productId]);
  }

  finalizeOrder(orderProduct: OrderProduct){
    this._orderService.finalizeOrder(orderProduct, !orderProduct.finalized).subscribe((res:OrderProduct) => {
      this.getOrdersPlaced();
    });
  }

}
