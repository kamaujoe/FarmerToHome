import { Component, OnInit } from '@angular/core';
import { Products } from '../products';
import { ProductsService } from '../products.service';
import { stringify } from '@angular/compiler/src/util';
import { Basket } from '../basket/basket';

@Component({
  selector: 'app-bakery-dairy',
  templateUrl: './bakery-dairy.component.html',
  styleUrls: ['./bakery-dairy.component.css']
})
export class BakeryDairyComponent implements OnInit {
  basketId : number
  products: Products[]
  currentProduct: Basket

  constructor(private productService: ProductsService) { 
    this.products=[]
    this.basketId = 75
  }

  // getProducts(){
  //   this.productService.getAllProducts().subscribe(
  //     res=>{this.products = res}
  //   )
  // }

  ngOnInit() {
    // this.productService.getAllProducts().subscribe(
    //      res=>{this.products = res}
    // )
    this.productService.fetchProductsByCategory(3).subscribe(
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
