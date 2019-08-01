import { Component, OnInit } from '@angular/core';
import { Products } from '../products';
import { ProductsService } from '../products.service';


@Component({
  selector: 'app-beverages',
  templateUrl: './beverages.component.html',
  styleUrls: ['./beverages.component.css']
})
export class BeveragesComponent implements OnInit {
  products: Products[]
  constructor(private productService: ProductsService) { 
    this.products=[]
  }

  ngOnInit() {
    this.productService.fetchProductsByCategory("BEVERAGES").subscribe(
      res => {
        this.products = res}
    )
  }

}
