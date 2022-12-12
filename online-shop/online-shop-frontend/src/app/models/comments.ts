import {Product} from "./product";
import {User} from "./user";

export class Comments {

  id: number = 0;
  product:Product = new Product();
  client:User =new User();
  description:string ='';
  date: string = ''

  constructor(){
  }



}
