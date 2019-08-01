import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { FarmerComponent } from './farmer/farmer.component';
import { Observable } from 'rxjs';
import { Farmer } from './farmer';

@Injectable({
  providedIn: 'root'
})
export class FarmerService {

  rootURL: string

  constructor(private httpsvc:HttpClient) {

    this.rootURL="http://localhost:8080/farmers"
   }



   findFarmerByFarmerId(farmerId):Observable<FarmerComponent>{
    return this.httpsvc.get<FarmerComponent>(
                  this.rootURL+"/find/"+farmerId)
  }

  loadAllFarmersFromServer():Observable<Farmer[]>{
    return this.httpsvc.get<Farmer[]>(
        "http://localhost:8080/farmers/list")
  }

   registerFarmerOnServer(farmer):Observable<FarmerComponent>{
    const httpOptions= {
      headers: new HttpHeaders(
        {"Content-Type":"application/x-www-form-urlencoded"})
    }
    // key1=value1&keyn=valuen
    var reqBody = "farmerId="+farmer.farmerId+"&farmerName="+
                      farmer.farmerName+"&products="+farmer.products+"&farmLocation="+farmer.farmLocation
    //  post(URL,body,httpOptionswithHeaders)
    
    return this.httpsvc.post<FarmerComponent>(
        this.rootURL+"/register",
        reqBody,httpOptions)
  }

}