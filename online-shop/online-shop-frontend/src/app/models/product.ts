import {Designer} from "./designer";
import { User } from "./user";

export class Product {

  id: number = 0;
  sketching:string ='';
  finalProduct:string ='';
  productName:string ='';
  price:number=0;
  description:string ='';
  designer: Designer = new Designer();
  status:string=''
  user: User = new User();
  constructor(){
  }

}
