import { Component, OnInit } from '@angular/core';
import {Router} from "@angular/router";
import {OrderService} from "../../services/order.service";
import {BillingDataService} from "../../services/billingData.service";
import {User} from "../../models/user";
import {NgForm} from "@angular/forms";
import {BillingData} from "../../models/billingData";
import {ShopCart} from "../../models/shopCart";
import {ShopCartService} from "../../services/shopCart.service";
import {OrderProduct} from "../../models/orderProduct";

@Component({
  selector: 'app-order-page',
  templateUrl: './order-page.component.html',
  styleUrls: ['./order-page.component.css']
})
export class OrderPageComponent implements OnInit {

  loggedUserStorage = localStorage.getItem('user');
  loggedUser: User = new User();
  errorMessage: string ='';
  billingDataObject: BillingData = new BillingData();
  products: ShopCart[] = [];
  totalPrice: number = 0;
  placedOrderBool:boolean = false;

  constructor(private _shopCartService: ShopCartService, private _orderService: OrderService,private _router: Router, private _billingDataService: BillingDataService) { }

  ngOnInit(): void {
    if(this.loggedUserStorage){
      this.loggedUser = JSON.parse(this.loggedUserStorage);
    }
    this.getProductsFromOrder();
  }

  placeOrder(placeOrderForm: NgForm) {
    if (placeOrderForm.form.value.address == '' || placeOrderForm.form.value.city == '' || placeOrderForm.form.value.country == ''
    || placeOrderForm.form.value.zip == '' || placeOrderForm.form.value.cardName == ''
    || placeOrderForm.form.value.cardnumber == '' || placeOrderForm.form.value.expmonth == ''
      || placeOrderForm.form.value.expyear == ''  || placeOrderForm.form.value.cvv == '') {
      this.errorMessage = "Toate câmpurile trebuie completate!"
      return;
    }

    this.errorMessage = '';

    const transientBillingData = new BillingData();
    transientBillingData.address = placeOrderForm.form.value.address;
    transientBillingData.city = placeOrderForm.form.value.city;
    transientBillingData.country = placeOrderForm.form.value.country;
    transientBillingData.zipcode = placeOrderForm.form.value.zip;

    this._billingDataService.addBillingData(transientBillingData).subscribe((res:BillingData) => {
      this.billingDataObject = res;
      this.makeOrder();
    });
  }

  makeOrder() {
    const transientOrder = new OrderProduct();
    transientOrder.client = this.loggedUser;
    transientOrder.orderDate = new Date().toISOString();
    transientOrder.billingData = this.billingDataObject;
    transientOrder.finalized = false;

    for (let i = 0; i < this.products.length; i++) {
      transientOrder.product = this.products[i].product;
      transientOrder.quantity = this.products[i].quantity;

      this._orderService.addOrder(transientOrder).subscribe((res:OrderProduct) =>
        this._shopCartService.deleteProductInCart(this.products[i].id).subscribe()
      );
      this.placedOrderBool=true;
      setTimeout(() =>  this._router.navigate(["/placedOrders"]), 1100)
    }
  }

  getProductsFromOrder(){
    return this._shopCartService.getAllProductsFromShopCart()
      .subscribe((res: ShopCart[]) => {
        this.products = res;
        this.getTotalPrice();
      })
  }

  getTotalPrice(){
    this.totalPrice = 0;
    for(let i = 0; i<this.products.length;i++){
      this.totalPrice += this.products[i].product.price * this.products[i].quantity;
    }
  }

}
