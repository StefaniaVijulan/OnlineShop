import { Pipe, PipeTransform } from '@angular/core';
import {Product} from "../models/product";

@Pipe({
  name: 'searchFilter'
})
export class SearchPipe implements PipeTransform {



  transform(list: Product[], filterText: string): any {
    if(!filterText) return list;
    if(!list) return null;

      return (list as Array<Product>).filter(
        element => element.productName.toLowerCase().includes(filterText.toLowerCase()));
    }

}
