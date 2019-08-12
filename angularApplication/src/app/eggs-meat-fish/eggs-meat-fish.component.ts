import { Component, OnInit } from '@angular/core';
import { Products } from '../products';
import { ProductsService } from '../products.service';
import { Basket } from '../basket/basket';

@Component({
  selector: 'app-eggs-meat-fish',
  templateUrl: './eggs-meat-fish.component.html',
  styleUrls: ['./eggs-meat-fish.component.css']
})
export class EggsMeatFishComponent implements OnInit {
   products: Products[]
   basketId : number
   currentProduct : Basket
   constructor(private productService: ProductsService) { 
     this.products=[]
     this.basketId = 75
  }

  ngOnInit() {
    this.productService.fetchProductsByCategory(4).subscribe(
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
