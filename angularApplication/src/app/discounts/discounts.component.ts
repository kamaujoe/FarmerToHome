import { Component, OnInit } from '@angular/core';
import { Products } from '../products';
import { ProductsService } from '../products.service';

@Component({
  selector: 'app-discounts',
  templateUrl: './discounts.component.html',
  styleUrls: ['./discounts.component.css']
})
export class DiscountsComponent implements OnInit {
  products: Products[]
  constructor(private productService: ProductsService) {
    this.products=[]
  }

  ngOnInit() {
    this.productService.fetchProductByExpiryDate(1,5).subscribe(
      res => {
        this.products = res}
    )
  }

}
