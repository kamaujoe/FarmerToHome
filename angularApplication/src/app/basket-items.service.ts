import { Injectable } from '@angular/core';
import { Basket } from './basket/basket';
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class BasketItemsService {
  rootURL : string

  constructor(private httpsvc:HttpClient) {
    this.rootURL = "http://localhost:8080/basket/"
  }

  getBasketItems(basketId):Observable<Basket>{
    return this.httpsvc.get<Basket>("http://localhost:8080/basket/find/" + basketId)
  }

  deleteFromBasket(productId, basketId):Observable<Basket>{ 
    const httpOptions = {
      headers: new HttpHeaders(
        {"Content-Type" : "application/x-www-form-urlencoded"}
      )
    }
    var reqBody = "productId=" + productId + "&basketId=" + basketId
      return this.httpsvc.post<Basket>(this.rootURL + "deleteFromBasket", reqBody, httpOptions)
  }

}
