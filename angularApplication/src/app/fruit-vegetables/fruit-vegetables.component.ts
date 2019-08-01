import { Component, OnInit } from '@angular/core';
import { Products } from '../products';
import { ProductsService } from '../products.service';

@Component({
  selector: 'app-fruit-vegetables',
  templateUrl: './fruit-vegetables.component.html',
  styleUrls: ['./fruit-vegetables.component.css']
})
export class FruitVegetablesComponent implements OnInit {
   products: Products[]
   constructor(private productService: ProductsService) { 
     this.products=[]
  }

  ngOnInit() {
    this.productService.fetchProductsByCategory("FRUIT_VEGETABLES").subscribe(
      res => {
        this.products = res}
    )
  }

}
