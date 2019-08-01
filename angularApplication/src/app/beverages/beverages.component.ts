import { Component, OnInit } from '@angular/core';
import { CategoriesService } from '../categories.service';

@Component({
  selector: 'app-beverages',
  templateUrl: './beverages.component.html',
  styleUrls: ['./beverages.component.css']
})
export class BeveragesComponent implements OnInit {
  productId : number
  productName : string
  price : number
  quantity : number
  size : string
  expiry_date : number
  category : string

  constructor(private bevsvc : CategoriesService) {
    this.category = "BAKERY_DAIRY"
   }

  ngOnInit() {
  }

fetchAllBevarages(){
  this.bevsvc.getProductsByCategory(this.category).subscribe(
  response => {
  this.productId = response.productId}) 
}

}
