import { Component, OnInit } from '@angular/core';
import { Products } from '../products';
import { ProductsService } from '../products.service';
import { Basket } from '../basket/basket';
import { Quantity } from '../basket/quantity';
import { OrderService } from '../order.service';
import { Order } from '../basket/order';








@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {


  
    currentBasket : Basket[]
    addQua : Quantity
    currentQuantity : Quantity[]
    updateQuantity : Quantity

    currentOrder : Order

    orderId : number

   
    productId : number
    basketId : number
    products: Products[]
    currentProduct : Basket

    

    constructor(private productService: ProductsService, private orderService: OrderService) {
      
    
     
<<<<<<< HEAD
      
      this.basketId = 14 
=======
      this.basketId = 1
>>>>>>> a3ff255f7fc51d9ccd0dfdde10c0ad31d137acaf
      this.products=[] }

  ngOnInit() {
    this.productService.getAllProducts().subscribe(
    res => {
      this.products = res}
      )
<<<<<<< HEAD
      this.getOrders(this.basketId)
   
   
  
    }

    getOrders(basketId){
      this.orderService.getOrderByBasket(basketId).subscribe(
        res => {this.currentQuantity = res}
      )
    }

    addQuantity(productId, quantity){
      console.log(this.basketId, productId) // 14, 37
      
      this.orderService.getOrderByBasketProduct(this.basketId, productId).subscribe(
        res => {
          
          this.currentOrder = res})
          
          
          //this.orderId = this.currentOrder.orderId
          
          console.log(this.currentOrder.orderId)
         

      if(this.currentOrder.orderId = 0){

      this.orderService.addOrders(productId, quantity, this.basketId).subscribe(
=======
    }

    addProducts(productId){
      this.productService.addProductsToBasket(productId, 
        this.basketId).subscribe(
>>>>>>> a3ff255f7fc51d9ccd0dfdde10c0ad31d137acaf
        response => {
          this.addQua = response
        }
      )}
      else{
        this.updateOrders(productId, quantity, this.currentOrder.orderId)
        
      }
    }
  
    updateOrders(productId, quantity, orderId){
      this.orderService.updateOrders(productId, quantity, this.basketId, orderId).subscribe(
        res => {
          this.updateQuantity = res
        }
      )
    }
  






    addProducts(productId){
      
      this.productService.addProductsToBasket(productId,this.basketId).subscribe(
        
        response => {this.currentProduct = response}
      )
    }  
}
