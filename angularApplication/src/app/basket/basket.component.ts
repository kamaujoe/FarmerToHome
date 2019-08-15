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
<<<<<<< HEAD

  currentOrder : Order
  currentBasket : Order[]
=======
  currentBasket : Basket
>>>>>>> c350aa2377a52f50881ae74482f4a8a9ed8f203d
  total : number
  quantity: number
  basketId : number

<<<<<<< HEAD
  

  constructor(private prodsvc: BasketItemsService, private orderService: OrderService) {

    this.basketId = 14 // use consumer login Id to pull basket Id
    
    
    
=======
  constructor(private prodsvc: BasketItemsService) {
    this.basketId = 14 // use consumer login Id to pull basket Id
>>>>>>> c350aa2377a52f50881ae74482f4a8a9ed8f203d
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
