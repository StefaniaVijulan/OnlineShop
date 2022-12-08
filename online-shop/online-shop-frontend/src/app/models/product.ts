import {Designer} from "./designer";

export class Product {

  id: number = 0;
  sketching:string ='';
  finalProduct:string ='';
  productName:string ='';
  price:number=0;
  description:string ='';
  designer: Designer = new Designer();

  constructor(){
  }

}
