import { Component, OnInit } from '@angular/core';
import { Products } from '../products';
import { ProductsService } from '../products.service';
import { Basket } from '../basket/basket';
import { Order } from '../basket/order';

@Component({
  selector: 'app-bakery-dairy',
  templateUrl: './bakery-dairy.component.html',
  styleUrls: ['./bakery-dairy.component.css']
})
export class BakeryDairyComponent implements OnInit {
  basketId : number
  products: Products[]
  currentProduct: Order

  constructor(private productService: ProductsService) { 
    this.products=[]
    this.basketId = 75
  }

  ngOnInit() {
    this.productService.fetchProductsByCategory(3).subscribe(
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
