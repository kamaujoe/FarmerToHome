import { Component, OnInit } from '@angular/core';
import { Products } from '../products';
import { ProductsService } from '../products.service';
import { Basket } from '../basket/basket';
import { Order } from '../basket/order';

@Component({
  selector: 'app-beverages',
  templateUrl: './beverages.component.html',
  styleUrls: ['./beverages.component.css']
})
export class BeveragesComponent implements OnInit {
  products: Products[]
  basketId : number
  currentProduct: Order

  constructor(private productService: ProductsService) { 
    this.products=[]
    this.basketId = 75
  }

  ngOnInit() {
    this.productService.fetchProductsByCategory(1).subscribe(
      response => {
        this.products = response
      }
    )
  }

  addProducts(productId){
    this.productService.addProductsToBasket(productId,this.basketId).subscribe(
      response => {
        this.currentProduct = response
      }
    )
  }

}
