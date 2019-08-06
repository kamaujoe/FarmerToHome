import { Injectable } from '@angular/core';
import { HttpClient,  HttpHeaders } from '../../node_modules/@angular/common/http'
import { Observable } from 'rxjs';
import { Products } from './products'
import { Product } from './basket/product';
import { Basket } from './basket/basket';

@Injectable({
  providedIn: 'root'
})
export class ProductsService {

  url: string
  constructor(private httpsvc:HttpClient) { 
    this.url="http://localhost:8080/product"
  }

  //simple get for all products
  getAllProducts():Observable<Products[]>{
    return this.httpsvc.get<Products[]>(this.url+"/allProducts")
  }

  //fetch by category
  fetchProductsByCategory(cat_name):Observable<Products[]>{
    return this.httpsvc.get<Products[]>(this.url+"/fetchByCategory?category="+cat_name)
  }

  addProductsToBasket(productId, basketId):Observable<Basket>{const httpOptions = {
    headers: new HttpHeaders(
      {"Content-Type" : "application/x-www-form-urlencoded"}
    )
  }
    var reqBody = "productId=" + productId + "&basketId=" + basketId
      return this.httpsvc.post<Basket>("http://localhost:8080/basket/assign/product", reqBody, httpOptions)
  }

  
  }


