import { Component, OnInit, Output } from '@angular/core';
import { FormArray, FormBuilder, FormControl, FormGroup, SelectControlValueAccessor, Validators } from '@angular/forms';
import { Subject } from 'rxjs';
import { PizzaOrder } from '../models';
import { PizzaService } from '../pizza.service';

const SIZES: string[] = [
  "Personal - 6 inches",
  "Regular - 9 inches",
  "Large - 12 inches",
  "Extra Large - 15 inches"
]

const PizzaToppings: string[] = [
    'chicken', 'seafood', 'beef', 'vegetables',
    'cheese', 'arugula', 'pineapple'
]

@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.css']
})
export class MainComponent implements OnInit {

  @Output()
  onNewOrder = new Subject<PizzaOrder>()

  pizzaSize = SIZES[0]
  email!: string
  order!: FormGroup
  selectedToppings!: FormArray

  constructor(private fb: FormBuilder, private pizzaSvc: PizzaService) {}

  ngOnInit(): void {
    this.order = this.createOrder()
    this.email = this.order.value.email
  }

  updateSize(size: string) {
    this.pizzaSize = SIZES[parseInt(size)]
  }

  onCheckboxChange(event: any) {
     this.selectedToppings = (this.order.controls['toppings'] as FormArray)
    if (event.target.checked) {
      this.selectedToppings.push(new FormControl(event.target.value));
    } else {
      const index = this.selectedToppings.controls
      .findIndex(x => x.value === event.target.value);
      this.selectedToppings.removeAt(index);
  }
  }

  private createOrder(): FormGroup {
    this.selectedToppings = this.fb.array([]);
    
    return this.fb.group({
      name: this.fb.control<string>('', [ Validators.required, Validators.minLength(5) ]),
      email: this.fb.control<string>('', [ Validators.required, Validators.email ]),
      size: this.fb.control<number>(0, [ Validators.required ]),
      base: this.fb.control<string>('', [ Validators.required ]),
      toppings: this.selectedToppings,
      sauce: this.fb.control<string>('', [ Validators.required ]),
      comments: this.fb.control<string>('', []),

    })
  }
  placeOrder() {
    const order = this.order.value as PizzaOrder
    this.onNewOrder.next(order)
      //this.order.reset()
    this.pizzaSvc.createOrder(order);
  }

}
