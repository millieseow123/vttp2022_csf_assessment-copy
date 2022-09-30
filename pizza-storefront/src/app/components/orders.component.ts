import { Component, OnInit } from '@angular/core';
import { FormArray } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { getOrderByEmail, PizzaOrder } from '../models';
import { PizzaService } from '../pizza.service';

@Component({
  selector: 'app-orders',
  templateUrl: './orders.component.html',
  styleUrls: ['./orders.component.css']
})
export class OrdersComponent implements OnInit {

  email!: string
  orders!: getOrderByEmail[]

  constructor(private activatedRoute: ActivatedRoute, private pizzaSvc: PizzaService) { }

  ngOnInit(): void {
    this.email = this.activatedRoute.snapshot.params['email']
    this.pizzaSvc.getOrders(this.email)
    .then(result => {
      this.orders = result
    })
    .catch(error => {
      console.error('>>>> error: ', error)
    })
  }

}
