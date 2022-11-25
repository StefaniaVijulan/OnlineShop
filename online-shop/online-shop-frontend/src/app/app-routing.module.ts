import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {AboutComponent} from "./pages/about/about.component";
import {ProductsTabComponent} from "./pages/products-tab/products-tab.component";
import {ProductDetailsComponent} from "./pages/products-tab/product-details/product-details.component";

const routes: Routes = [
  {
    path:"",
    component: AboutComponent
  },
  {
    path:"products",
    component: ProductsTabComponent
  },
  {
    path: 'productDetails',
    component: ProductDetailsComponent
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})

export class AppRoutingModule { }
