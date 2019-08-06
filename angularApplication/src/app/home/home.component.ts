import { Component, OnInit } from '@angular/core';
import { Products } from '../products';
import { ProductsService } from '../products.service';
import { Basket } from '../basket/basket';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

    productId : number
    basketId : number
    products: Products[]
    currentProduct : Basket

    constructor(private productService: ProductsService) {
      this.basketId = 75 
      this.products=[] }

  ngOnInit() {
    this.productService.getAllProducts().subscribe(
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
