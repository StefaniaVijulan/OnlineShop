import { Component, OnInit } from '@angular/core';
import {Product} from "../../models/product";
import {HttpClient} from "@angular/common/http";
import {ProductService} from "../../services/product.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-products-tab',
  templateUrl: './products-tab.component.html',
  styleUrls: ['./products-tab.component.css']
})
export class ProductsTabComponent implements OnInit {

  /* productsList: Product[] = [
     { idProduct: 1, sketching: "",finalProduct: "", productName:"Fustă",price:150,description:"Fustă de lungime medie" },
     { idProduct: 2, sketching: "",finalProduct: "", productName:"Bluză groasă",price:40,description:"Bluza potrivită pentru o vreme rece, cu material catifelat"},
     { idProduct: 3, sketching: "",finalProduct: "", productName:"Ținută completă",price:200,description:"Setul cuprinde o bluză împreună cu o pereche de pantaloni. La aceasta ținută se adaugă un palton și o pereche de ghete." },
     { idProduct: 4, sketching: "",finalProduct: "", productName:"Sacou office",price:600,description:"Sacou de culoare neagră, potrivit pentru o zi la birou. Materialul este unul subțire și lejer."},
     { idProduct: 5, sketching: "",finalProduct: "", productName:"Rochie de vară",price:800,description:"Rochie dintr-un material elastic și răcoros. Lungime scurtă și imprimeu floral."}
   ];
   */
  searchText: string = "";
  productsList: Product[] = [];

  constructor(private _httpClient: HttpClient, private _router:Router, private _productService: ProductService) { }

  ngOnInit(): void {
    this.getProducts();
  }

  getProducts(){
    return this._productService.getAllProducts()
      .subscribe((res: Product[]) => {
        this.productsList = res;
      })
  }

  goToProductShow(productId:number){
    this._router.navigate(["/productDetails/" + productId]);
  }
}
