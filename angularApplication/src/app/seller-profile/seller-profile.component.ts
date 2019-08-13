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
  firstName: String
  lastName: String
  email: String
  address: String
  phone: number
  farmerUsername: String
  farmerPassword: String
  
  farmers: Farmer[]

  allFarmers: Farmer[]
  assignedProducts: Product[]

  
  isEditable: boolean
  isSellerFormVisable: boolean

  isSellerFormValid: boolean
  invalidFormMessage: String

  isProductFormVisable:boolean
  isProductFormValid:boolean

  farmerProds: Product[]
  selectProductId: number

  
  constructor(private farmerSvc:SellerService) { 

    this.isEditable=false
    this.isSellerFormVisable=false
    this.isSellerFormValid=true

    this.isProductFormVisable=false
    this.isProductFormValid=true

    this.farmerId=1
    this.firstName="Joe"
    this.lastName="Farmer"
    this.address="Address"
    this.email="Email"
    this.phone=12345
    this.farmerUsername="Username"
    this.farmerPassword="Password"
    
    this.farmerProds =
    [
      {productId:4,product_name:"Rice",expiry_date:22/12/2019,size:"Large",price:1.99,currentCategory:[]}
    ]
  }

  ngOnInit() {
    this.fetchCurrentSellerFromService()
  }

  fetchCurrentSellerFromService() {
    this.farmerSvc.findFarmerByFarmerId(this.farmerId).subscribe(
      response => {
        this.farmerId = response.farmerId
        this.firstName = response.firstName
        this.lastName = response.lastName
        this.address = response.address
        this.phone = response.phone
        this.email = response.email
        this.farmerUsername = response.farmerUsername
        this.farmerPassword = response.farmerPassword
        
        this.farmerProds = response.farmerProds
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
    this.loadFarmerProducts()
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
            console.log(response)
          })
  }

  deleteProduct(index, productId) {
    this.farmerProds.splice(index, 1, productId)
  }

  addNewProduct(pproductId,pproduct_name,pprice,psize,pexpiry_date,pcurrentCategory) {
    if(isNaN(pproductId))
    {
      this.isProductFormValid=false
      this.invalidFormMessage="Product ID must be a number"
    }
    else if(pproduct_name.length<4){
      this.isProductFormValid=false
      this.invalidFormMessage="Product name must be greater than 4 characters"
    }
    else {
      this.farmerProds.push({
        productId:pproductId,
        product_name:pproduct_name,
        price:pprice,
        size:psize,
        expiry_date:pexpiry_date,
        currentCategory:pcurrentCategory
      })
      this.isProductFormVisable=false
      this.isProductFormValid=true
      this.invalidFormMessage=""
    }

  }

  updateSellerDetails() {
    this.farmerSvc.updateFarmerOnServer({
      farmerId:this.farmerId, 
      firstName:this.firstName,
      lastName:this.lastName,
      address:this.address, 
      phone:this.phone,
      email:this.email,
      farmerUsername:this.farmerUsername,
      farmerPassword:this.farmerPassword
    }).subscribe(
      response => {
        this.fetchCurrentSellerFromService()
      }
    )
  }

}
