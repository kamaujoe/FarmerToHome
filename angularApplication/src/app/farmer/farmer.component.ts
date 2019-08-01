import { Component, OnInit } from '@angular/core';
import { Farmer } from '../farmer';
import { FarmerService } from '../farmer.service';

@Component({
  selector: 'app-farmer',
  templateUrl: './farmer.component.html',
  styleUrls: ['./farmer.component.css']
})
export class FarmerComponent implements OnInit {

  farmerId: number
  farmerName: string
  products: string
  farmLocation: string

  farmers: Farmer[]

  allFarmers: Farmer[]

  constructor(private farmerSvc:FarmerService) { 

    this.farmerId = 7
    this.farmerName = "Oldman Joe's"
    this.products = "vegetables, fruits and more!"
    this.farmLocation = "Leeds"

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
