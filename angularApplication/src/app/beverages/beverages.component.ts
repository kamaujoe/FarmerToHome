import { Component, OnInit } from '@angular/core';
import { Products } from '../products';
import { ProductsService } from '../products.service';
import { Basket } from '../basket/basket';


@Component({
  selector: 'app-beverages',
  templateUrl: './beverages.component.html',
  styleUrls: ['./beverages.component.css']
})
export class BeveragesComponent implements OnInit {
  products: Products[]
  basketId : number
  currentProduct : Basket

  constructor(private productService: ProductsService) {
    this.basketId = 75  
    this.products=[]
  }

  ngOnInit() {
    this.productService.fetchProductsByCategory(80).subscribe(
      res => {
        this.products = res}
    )
  }

  addProducts(productId){
    this.productService.addProductsToBasket(productId, 
      this.basketId).subscribe(
      response => {
        this.currentProduct = response
      }
    )
  }
}
