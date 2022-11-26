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



@NgModule({
  declarations: [
    AppComponent,
    AboutComponent,
    ProductsTabComponent,
    SearchPipe,
    ProductDetailsComponent,
    LoginComponent,
    RegisterComponent
    
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
    ReactiveFormsModule
  ],

  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
