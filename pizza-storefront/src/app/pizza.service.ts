// Implement the methods in PizzaService for Task 3
// Add appropriate parameter and return type 

import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { firstValueFrom } from "rxjs";
import { getOrderByEmail, PizzaOrder } from "./models";

@Injectable()
export class PizzaService {

  constructor(private http : HttpClient) { }

  // POST /api/order
  // Add any required parameters or return type
  createOrder(pizzaOrder: PizzaOrder) { 
    console.log('angular service ', pizzaOrder);
    const headers = new HttpHeaders()
        .set('Content-Type', 'application/json')
        .set('Accept', 'application/json')
    return firstValueFrom(
      this.http.post<any>('/api/order', pizzaOrder, { headers })
    )
  }

  // GET /api/order/<email>/all
  // Add any required parameters or return type
  getOrders(email: string): Promise<getOrderByEmail[]> { 
    return firstValueFrom(
      this.http.get<getOrderByEmail[]>(`/api/order/${email}/all`)
    )
  }

}
