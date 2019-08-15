import { Component, OnInit } from '@angular/core';
import { Basket } from './basket';
import { BasketItemsService } from '../basket-items.service';
import { OrderService } from '../order.service';
import { Order } from './order';

@Component({
  selector: 'app-basket',
  templateUrl: './basket.component.html',
  styleUrls: ['./basket.component.css']
})
export class BasketComponent implements OnInit {

  currentOrder : Order
  currentBasket : Order[]
  total : number
  quantity: number
  basketId : number



  constructor(private prodsvc: BasketItemsService, private orderService: OrderService) {

    this.basketId = 14 // use consumer login Id to pull basket Id
    
    
    
  }
  
  ngOnInit() {
    this.currentBasket = []
    this.fetchCurrentProductsFromBasket()
    this.calculateTotal()
  }

  deleteProduct(productId){
    
    
    this.orderService.deleteFromBasket(productId, this.basketId).subscribe(
      response => {
        this.currentBasket = response
        console.log(this.currentBasket)
      }
    )
    
  }

  fetchCurrentProductsFromBasket(){
    this.prodsvc.getBasketItems(this.basketId).subscribe( 
      response => {
        
        this.currentBasket = response
      console.log(this.currentBasket)}
   
    )
  }

  updateQuantity(quantity, productId){
    this.orderService.addOrders(productId, quantity, this.basketId).subscribe(
      res => {this.currentBasket = res}
    )
    
  }
    
  calculateTotal(){ 
    this.orderService.calculateTotal(this.basketId).subscribe(
      res => {this.total = res}
    )     
  }
 
  emptyBasket(){
    this.orderService.emptyBasket(this.basketId).subscribe(
      res => {this.currentBasket = res}
    )
  }

}
