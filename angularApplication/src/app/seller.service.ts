import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { SellerProfileComponent } from './seller-profile/seller-profile.component';

@Injectable({
  providedIn: 'root'
})
export class SellerService {

  rootURL: String

  constructor(private httpsvc:HttpClient) {

    this.rootURL="http://localhost:9999/seller-profile"
   }

   findFarmerByFarmerId(farmerId):Observable<SellerProfileComponent> {
     return this.httpsvc.get<SellerProfileComponent>
     (this.rootURL+"/find/"+farmerId)
   }

   updateFarmerOnServer(farmer):Observable<SellerProfileComponent> {
     const httpOptions = {
       headers: new HttpHeaders(
         {"Content-Type":"application/x-ww-form-urlencoded"}
       )
     }
     var reqBody = "farmerId="+farmer.farmerId+"&farmerName="+farmer.farmerName+"&farmerLocation="+farmer.farmerLocation+"&products="+farmer.products
     return this.httpsvc.post<SellerProfileComponent>(
                                    this.rootURL+"/register", 
                                    reqBody,httpOptions
     )
   }

}
