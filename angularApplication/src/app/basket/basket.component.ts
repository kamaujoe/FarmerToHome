import { Component, OnInit } from '@angular/core';
import { Basket } from './basket';
import { Product } from './product';

@Component({
  selector: 'app-basket',
  templateUrl: './basket.component.html',
  styleUrls: ['./basket.component.css']
})
export class BasketComponent implements OnInit {

  allProducts : Product[]
  currentProducts : Product[]
  currentBasket : Basket[]
  subTotal : number


  constructor() {
    this.currentBasket = [{
      basketId : 1, 
      productId : 2, 
      consno : 1
     }]

    this.allProducts = [{
      productId : 1,
      productName : "Rice",
      productPrice : 5.5,
      productQuantity : 2,
      productSize : "Medium"},
    {
      productId : 4,
      productName : "Apples",
      productPrice : 2.3,
      productQuantity : 10,
      productSize : "Small"},
    {
      productId : 2,
      productName : "Fish",
      productPrice : 9,
      productQuantity : 2,
      productSize : "Large"
    }]
    }
    
    //starting to create current project section from all products.

  ngOnInit() {
  }

  deleteProduct(index){
    this.currentBasket.splice(index, 1)
  }

  fetchProductIdFromBasket(){
  }

  calculateSubtotal(price, quantity){
    this.subTotal = price*quantity
    return this.subTotal
  }
  calculateTotal(){   
  }

}
