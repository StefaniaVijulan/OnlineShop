import {Product} from "./product";
import {User} from "./user";
import {BillingData} from "./billingData";

export class OrderProduct {

  id: number = 0;
  billingData: BillingData = new BillingData();
  product:Product = new Product();
  client:User =new User();
  finalized:boolean =false;
  quantity:number=0;
  orderDate: string = '';


  constructor(){
  }

}
