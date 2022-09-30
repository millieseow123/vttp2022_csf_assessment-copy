import { HttpClientModule } from '@angular/common/http'
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { RouterModule, Routes } from '@angular/router';

import { AppComponent } from './app.component';
import { MainComponent } from './components/main.component';
import { PizzaService } from './pizza.service';
import { OrdersComponent } from './components/orders.component';

const appPath: Routes = [
  { path: '', component: MainComponent },
  { path: 'order/:email', component: OrdersComponent },
  { path: '**', redirectTo: '/', pathMatch: 'full' }
]

@NgModule({
  declarations: [
    AppComponent, MainComponent, OrdersComponent
  ],
  imports: [
    BrowserModule, FormsModule, ReactiveFormsModule, HttpClientModule, RouterModule.forRoot(appPath)
  ],

  providers: [PizzaService ],
  bootstrap: [AppComponent]
})
export class AppModule { }
