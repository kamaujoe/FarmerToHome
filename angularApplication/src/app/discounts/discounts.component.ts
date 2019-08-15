import { Component, OnInit } from '@angular/core';
import { Products } from '../products';
import { ProductsService } from '../products.service';
import { Basket } from '../basket/basket';
import { Order } from '../basket/order';

@Component({
  selector: 'app-discounts',
  templateUrl: './discounts.component.html',
  styleUrls: ['./discounts.component.css']
})
export class DiscountsComponent implements OnInit {
  products: Products[]
  basketId : number
  currentProduct : Order

  constructor(private productService: ProductsService) {
    this.basketId = 75
    this.products=[]
  }

  ngOnInit() {
    this.productService.fetchProductByExpiryDate(1,5).subscribe(
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
