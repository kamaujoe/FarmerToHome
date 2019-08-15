
import { Component, OnInit } from '@angular/core';
import { Products } from '../products';
import { ProductsService } from '../products.service';
import { Basket } from '../basket/basket';
import { Quantity } from '../basket/quantity';
import { OrderService } from '../order.service';
import { Order } from '../basket/order';
import { BasketItemsService } from '../basket-items.service';

@Component({
  selector: 'app-fruit-vegetables',
  templateUrl: './fruit-vegetables.component.html',
  styleUrls: ['./fruit-vegetables.component.css']
})

export class FruitVegetablesComponent implements OnInit {
    currentBasket : Basket[]
    addQua : Order[]
    currentQuantity : Quantity[]
    updateQuantity : Quantity

    AllQuantities : number[]
    currentOrder : Order[]
    quantity : number
    orderId : number

   
    productId : number
    basketId : number
    products: Products[]
    currentProduct : Order

   constructor(private productService: ProductsService) { 
    this.basketId = 14
    this.products=[]
  }

  ngOnInit() {
    this.productService.fetchProductsByCategory(2).subscribe(
      response => {
        this.products = response
      }
    )
  }

  addProducts(productId){
    this.productService.addProductsToBasket(productId, this.basketId).subscribe(
      response => {
        this.currentProduct = response
      }
    )
  }
}
