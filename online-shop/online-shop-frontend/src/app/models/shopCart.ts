import {Product} from "./product";
import {User} from "./user";

export class ShopCart {

  id: number = 0;
  product:Product = new Product();
  client:User =new User();
  savedProduct:boolean =false;
  quantity:number=0;


  constructor(){
  }

}
