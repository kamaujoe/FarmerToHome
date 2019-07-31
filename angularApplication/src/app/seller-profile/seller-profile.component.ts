import { Component, OnInit } from '@angular/core';
import { SellerService } from '../seller.service';

@Component({
  selector: 'app-seller-profile',
  templateUrl: './seller-profile.component.html',
  styleUrls: ['./seller-profile.component.css']
})
export class SellerProfileComponent implements OnInit {

  farmerId: number
  farmerName: String
  farmLocation: String
  products: String

  isEditable: boolean
  isSellerFormVisable: boolean

  isSellerFormValid: boolean
  invalidFormMessage: boolean

  constructor(private farmerSvc:SellerService) { 
    this.isEditable=false
    this.isSellerFormVisable=false
    this.isSellerFormValid=true

    this.farmerId=3
    this.farmerName="Farmer Joe"
    this.farmLocation="Leeds"
    this.products="Whole Foods"
  }

  ngOnInit() {
    this.fetchCurrentSellerFromService()
  }

  fetchCurrentSellerFromService() {
    this.farmerSvc.findFarmerByFarmerId(this.farmerId).subscribe(
      response => {
        this.farmerId = response.farmerId
        this.farmerName = response.farmerName
        this.farmLocation = response.farmLocation
        this.products = response.products
      }
    )
  }

  toggleEdits() {
    this.isEditable = !this.isEditable
    this.updateSellerDetails()
  }

  updateSellerDetails() {
    this.farmerSvc.updateFarmerOnServer({
      farmerId:this.farmerId, farmerName:this.farmerName,
      farmLocation:this.farmLocation, products:this.products
    }).subscribe(
      response => {
        this.fetchCurrentSellerFromService()
      }
    )
  }

}
