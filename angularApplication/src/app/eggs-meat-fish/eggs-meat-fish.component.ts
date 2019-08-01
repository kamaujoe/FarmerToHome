import { Component, OnInit } from '@angular/core';
import { Products } from '../products';
import { ProductsService } from '../products.service';

@Component({
  selector: 'app-eggs-meat-fish',
  templateUrl: './eggs-meat-fish.component.html',
  styleUrls: ['./eggs-meat-fish.component.css']
})
export class EggsMeatFishComponent implements OnInit {
   products: Products[]
   constructor(private productService: ProductsService) { 
     this.products=[]
  }

  ngOnInit() {
    this.productService.fetchProductsByCategory("EGGS_MEAT_FISH").subscribe(
      res => {
        this.products = res}
    )
      }
  }
