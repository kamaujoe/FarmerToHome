import { Component, OnInit } from '@angular/core';
import { SellerService } from '../seller.service';
import { Farmer } from '../farmer';
import { Product } from '../basket/product';

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
  
  assignedProducts: Product[]

  farmerProds: Product[]
  selectProductId: number

  farmers: Farmer[]

  allFarmers: Farmer[]

  
  isEditable: boolean
  isSellerFormVisable: boolean

  isSellerFormValid: boolean
  invalidFormMessage: String

  isProductFormVisable:boolean
  isProductFormValid:boolean


  
  constructor(private farmerSvc:SellerService) { 

    this.isEditable=false
    this.isSellerFormVisable=false
    this.isSellerFormValid=true

    this.isProductFormVisable=false
    this.isProductFormValid=true

    this.farmerId=8
    this.farmerName="Farmer Joe"
    this.farmLocation="Leeds"
    this.products="Whole Foods"
    
    // this.assignedProducts =
    // [
    //   {productId:4,name:"Rice",quantity:1,expiry_date:22/12/2019,size:"Large",price:1.99,category:"BAKERY_DAIRY"}
    // ]
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
        
        this.assignedProducts = response.assignedProducts
      }
    )
  }

  toggleEdits() {
    this.isEditable = !this.isEditable
    this.updateSellerDetails()
  }

  showProductForm() {
    this.isProductFormVisable = true

    this.loadFarmerProducts()
  }

  updateSelectedProductId(productId) {
    this.selectProductId=productId
  }

  assignNewProduct() {
    this.farmerSvc.assignProductToSeller(
      this.farmerId,this.selectProductId
    ).subscribe(
      response => {
        this.fetchCurrentSellerFromService()
      }
    )
    this.isProductFormVisable=false
  }

  loadFarmerProducts() {
    this.farmerSvc.loadAllProductsFromServer()
        .subscribe(
          response =>{
            this.farmerProds = response
          })
  }

  deleteProduct(index) {
    this.assignedProducts.splice(index, 1)
  }

  // addNewProduct(pproductId,pproductName,pprice,pquantity,psize,pexpiry_date,pcategory) {
  //   if(isNaN(pproductId))
  //   {
  //     this.isProductFormValid=false
  //     this.invalidFormMessage="Product ID must be a number"
  //   }
  //   else if(pproductName.length<4){
  //     this.isProductFormValid=false
  //     this.invalidFormMessage="Product name must be greater than 4 characters"
  //   }
  //   else {
  //     this.assignedProducts.push({
  //       productId:pproductId,
  //       name:pproductName,
  //       price:pprice,
  //       quantity:pquantity,
  //       size:psize,
  //       expiry_date:pexpiry_date,
  //       category:pcategory
  //     })
  //     this.isProductFormVisable=false
  //     this.isProductFormValid=true
  //     this.invalidFormMessage=""
  //   }

  // }

  updateSellerDetails() {
    this.farmerSvc.updateFarmerOnServer({
      farmerId:this.farmerId, 
      farmerName:this.farmerName,
      farmLocation:this.farmLocation, 
      products:this.products,
    }).subscribe(
      response => {
        this.fetchCurrentSellerFromService()
      }
    )
  }

}
