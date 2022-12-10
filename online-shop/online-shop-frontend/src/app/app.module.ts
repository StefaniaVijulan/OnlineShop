import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import {HttpClientModule} from "@angular/common/http";
import { AboutComponent } from './pages/about/about.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { ProductsTabComponent } from './pages/products-tab/products-tab.component';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {MatCardModule} from "@angular/material/card";
import {MatButtonModule} from "@angular/material/button";
import {SearchPipe} from "./services/search.pipe";
import { ProductDetailsComponent } from './pages/products-tab/product-details/product-details.component';
import { LoginComponent } from './pages/login/login.component';
import { RegisterComponent } from './pages/register/register.component';
import { DesignersTabComponent } from './pages/designers-tab/designers-tab.component';
import { ShopCartComponent } from './pages/shop-cart/shop-cart.component';
import { SavedProductsComponent } from './pages/saved-products/saved-products.component';
import { OrderPageComponent } from './pages/order-page/order-page.component';
import { PlacedOrdersComponent } from './pages/placed-orders/placed-orders.component';
import { SchiteComponent } from './pages/schite/schite.component';
import { AddSchitaComponent } from './dialog/add-schita/add-schita.component';
import {MatDialogModule} from '@angular/material/dialog';
import { MatOptionModule } from '@angular/material/core';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from "@angular/material/form-field";
import {MatSelectModule} from '@angular/material/select';
@NgModule({
  declarations: [
    AppComponent,
    AboutComponent,
    ProductsTabComponent,
    SearchPipe,
    ProductDetailsComponent,
    LoginComponent,
    RegisterComponent,
    DesignersTabComponent,
    ShopCartComponent,
    SavedProductsComponent,
    OrderPageComponent,
    PlacedOrdersComponent,
    SchiteComponent,
    AddSchitaComponent
    
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    NgbModule,
    FormsModule,
    MatCardModule,
    MatButtonModule,
    BrowserAnimationsModule,
    FormsModule, 
    ReactiveFormsModule,
    MatDialogModule,
    MatFormFieldModule,
    MatInputModule,
    MatOptionModule,
    MatSelectModule
    
  ],
  exports: [ MatFormFieldModule, MatInputModule ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
