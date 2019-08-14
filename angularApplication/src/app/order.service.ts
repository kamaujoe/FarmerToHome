import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Quantity } from './basket/quantity';
import { Order } from './basket/order';

@Injectable({
  providedIn: 'root'
})
export class OrderService {
  url: string

  constructor(private httpsvc: HttpClient) {
    this.url="http://localhost:8080/order/"
   }

updateOrders(productId, quantity, basketId, orderId):Observable<Quantity>{
  const httpOptions = {
    headers: new HttpHeaders(
      {"Content-Type" : "application/x-www-form-urlencoded"}
    )
  }
  var reqBody = "productId=" + productId + "&basketId=" + basketId + "&quantity=" + quantity + "&orderId=" + orderId
    return this.httpsvc.post<Quantity>(this.url + "register", reqBody, httpOptions) 
}

getOrderByBasket(basketId):Observable<Quantity[]>{
  return this.httpsvc.get<Quantity[]>(this.url+"fetchByBasketId/"  + basketId)
}

// getOrderByBasketProduct(basketId, productId):Observable<Order>{
//   return this.httpsvc.get<Order>(this.url + "fetchByProductId/" + productId + "/" + basketId)
// }

getOrderByBasketProduct(basketId, productId):Observable<Order>{
  return this.httpsvc.get<Order>(this.url + "fetchByProductId/" + productId + "/" + basketId)
}

addOrders(productId, quantity, basketId):Observable<Quantity>{const httpOptions = {
  headers: new HttpHeaders(
    {"Content-Type" : "application/x-www-form-urlencoded"}
  )
}
  var reqBody = "productId=" + productId + "&basketId=" + basketId + "&quantity=" + quantity 
    return this.httpsvc.post<Quantity>(this.url + "register", reqBody, httpOptions) 
}



}