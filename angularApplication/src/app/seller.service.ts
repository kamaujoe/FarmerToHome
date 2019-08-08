import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { SellerProfileComponent } from './seller-profile/seller-profile.component';
import { Farmer } from './farmer';
import { Product } from './basket/product';
import { Category } from './category';
import { FarmerComponent } from './farmer/farmer.component';

@Injectable({
  providedIn: 'root'
})
export class SellerService {

  rootURL: String

  constructor(private httpsvc:HttpClient) {

    this.rootURL="http://localhost:8080/farmer"
   }

   findFarmerByFarmerId(farmerId):Observable<SellerProfileComponent> {
     return this.httpsvc.get<SellerProfileComponent>
     (this.rootURL+"/find/"+farmerId)
   }

   updateFarmerOnServer(farmer):Observable<SellerProfileComponent> {
     const httpOptions = {
       headers: new HttpHeaders(
         {"Content-Type":"application/x-www-form-urlencoded"}
       )
     }
     var reqBody = "farmerId="+farmer.farmerId+"&farmerName="+farmer.farmerName+"&farmLocation="+farmer.farmLocation+"&products="+farmer.products+"&farmerProds="+farmer.farmerProds
     return this.httpsvc.post<SellerProfileComponent>(
                                    this.rootURL+"/register", 
                                    reqBody,httpOptions
     )
   }

  //  loadAllFarmersFromServer():Observable<Farmer[]>{
  //   return this.httpsvc.get<Farmer[]>(
  //       "http://localhost:8080/farmer/list")
  // }

  loadAllProductsFromServer() {
    return this.httpsvc.get<Product[]>(
          "http://localhost:8080/product/list "
    )
  }

  loadAllCategoriesFromServer():Observable<Category[]> {
    return this.httpsvc.get<Category[]>(
            "http://localhost:8080/category/allCategories"
    )
  }

  assignProductToSeller(farmerId,productId):Observable<Product[]> {
    const httpOptions= {
      headers: new HttpHeaders(
        {"Content-Type":"application/x-www-form-urlencoded"})
    }
    var reqBody ="farmerId="+farmerId+"&productId="+productId

    return this.httpsvc.post<Product[]>(
      this.rootURL+"/assign/product",reqBody,httpOptions
    )
  }

  updateSellerCategoryOnServer(farmerId,categoryId):Observable<FarmerComponent> {
    const httpOptions= {
      headers: new HttpHeaders(
        {"Content-Type":"application/x-www-form-urlencoded"}
      )
    }
    var reqBody ="farmerId="+farmerId+"&categoryId="+categoryId
    return this.httpsvc.post<FarmerComponent>(
      this.rootURL+"/assign/category",
      reqBody,httpOptions
    )
  }
   
   

}
