import { Component, OnInit } from '@angular/core';
import { Basket } from './basket';
import { Product } from './product';

import { JsonPipe } from '@angular/common';
import { BasketItemsService } from '../basket-items.service';

@Component({
  selector: 'app-basket',
  templateUrl: './basket.component.html',
  styleUrls: ['./basket.component.css']
})

export class BasketComponent implements OnInit {

  
  
  currentBasket : Basket
  total : number

 

  basketId : number

  

  constructor(private prodsvc: BasketItemsService) {

    this.basketId = 14 // use consumer login Id to pull basket Id
    
  }
    


  ngOnInit() {
    this.fetchCurrentProductsFromBasket()
  }

  deleteProduct(index, productId){
    this.currentBasket.items.splice(index, 1)
    
    this.prodsvc.deleteFromBasket(productId, this.basketId).subscribe(
      response => {
        this.currentBasket = response
      }
    )
  }

  fetchCurrentProductsFromBasket(){
    this.prodsvc.getBasketItems(this.basketId).subscribe( 
      response => {
        
        this.currentBasket = response}
   
    )
  }
    
  calculateTotal(){   
    this.currentBasket.items.forEach(currentBasketItem => 
      {this.total+=(currentBasketItem.price)*(currentBasketItem.quantity)}
      )
    
  }

}
