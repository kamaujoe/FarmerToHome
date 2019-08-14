import { Component, OnInit } from '@angular/core';
import { Farmer } from '../farmer';
import { FarmerService } from '../farmer.service';
import { SellerLoginAuthenticationService } from '../seller-login-authentication.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-seller-login-registration',
  templateUrl: './seller-login-registration.component.html',
  styleUrls: ['./seller-login-registration.component.css']
})
export class SellerLoginRegistrationComponent implements OnInit {

  farmerId: number
  farmerName: String
  products: String
  farmLocation: String

  //-> joe
  email = ""
  farmerPassword = ""
  invalidLogin = false

  //->end


  farmers: Farmer[]

  allFarmers: Farmer[]

  constructor(private farmerSvc:FarmerService, private router: Router, private authenSvc:SellerLoginAuthenticationService) { 

    this.farmerId 
    this.farmerName 
    this.products 
    this.farmLocation 

    //->joe
    this.email = "kfermin2@springer.com"
    this.farmerPassword = "WyMYIE"
    //->end
  }

  ngOnInit() {
    this.fetchCurrentFarmerFromService()
  }

  // // register farmer in memory
  // registerNewFarmer(fName, fprods, flocation){

  //   this.farmers.push({farmerName:fName, products:fprods, farmLocation:flocation})
 
  // }

  showFarmer(){
    this.loadAllFarmers()
  }

  ////-> Joe
  checkLogin(){
    if (this.authenSvc.authenticate(this.email, this.farmerPassword)){
      this.router.navigate([""])
      this.invalidLogin = false 
    } else 
      this.invalidLogin = true
  }

  // fetchCurrentSellerFromService(){
  //   this.
  // }

  //-> end




  fetchCurrentFarmerFromService(){
    this.farmerSvc.findFarmerByFarmerId(this.farmerId).subscribe(
        // use the response to initialize the component properties
      response => { // assign the data received from server
          // as response to the current component
          this.farmerId = response.farmerId
          this.farmerName = response.farmerName
          this.products=response.products
          this.farmLocation = response.farmLocation

      } 
    )
  }

  registerFarmerDetails(){
    this.farmerSvc.registerFarmerOnServer({
      farmerId:this.farmerId, farmerName: this.farmerName, products:this.products, farmLocation:this.farmLocation
    }).subscribe(
      response =>{ // perform the following operation on successful post
              this.fetchCurrentFarmerFromService()
          } 
        )
      
  }

  loadAllFarmers(){
    this.farmerSvc.loadAllFarmersFromServer()
        .subscribe(
            response =>{
                this.allFarmers = response
        })
  }

}
