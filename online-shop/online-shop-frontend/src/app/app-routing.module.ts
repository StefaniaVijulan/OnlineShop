import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {AboutComponent} from "./pages/about/about.component";
import {ProductsTabComponent} from "./pages/products-tab/products-tab.component";
import {ProductDetailsComponent} from "./pages/products-tab/product-details/product-details.component";
import { LoginComponent } from './pages/login/login.component';
import { RegisterComponent } from './pages/register/register.component';
import {DesignersTabComponent} from "./pages/designers-tab/designers-tab.component";
import {ShopCartComponent} from "./pages/shop-cart/shop-cart.component";
import {SavedProductsComponent} from "./pages/saved-products/saved-products.component";
import {OrderPageComponent} from "./pages/order-page/order-page.component";
import {PlacedOrdersComponent} from "./pages/placed-orders/placed-orders.component";
import { SchiteAsignateComponent } from './pages/schite-asignate/schite-asignate.component';

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
    path: 'productDetails/:productId',
    component: ProductDetailsComponent
  },
  {
    path: 'inregistrare',
    component: LoginComponent
  },
  {
    path: 'autentificare',
    component: RegisterComponent
  },
  {
    path: 'designers',
    component: DesignersTabComponent
  },
  {
    path: 'shopCart',
    component: ShopCartComponent
  },
  {
    path: 'savedProducts',
    component: SavedProductsComponent
  },
  {
    path: 'order',
    component: OrderPageComponent
  },
  {
    path: 'placedOrders',
    component: PlacedOrdersComponent
  },
  {
    path: 'schiteAsignate',
    component: SchiteAsignateComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})

export class AppRoutingModule { }
