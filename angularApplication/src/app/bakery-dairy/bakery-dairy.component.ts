import { Component, OnInit } from '@angular/core';
import { Products } from '../products';
import { ProductsService } from '../products.service';

@Component({
  selector: 'app-bakery-dairy',
  templateUrl: './bakery-dairy.component.html',
  styleUrls: ['./bakery-dairy.component.css']
})
export class BakeryDairyComponent implements OnInit {

  products: Products[]
  constructor(private productService: ProductsService) { 
    this.products=[]
  }

  // getProducts(){
  //   this.productService.getAllProducts().subscribe(
  //     res=>{this.products = res}
  //   )
  // }

  ngOnInit() {
    this.productService.getAllProducts().subscribe(
         res=>{this.products = res}
    )
  }


}
