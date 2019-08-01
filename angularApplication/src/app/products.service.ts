import { Injectable } from '@angular/core';
import { HttpClient,  HttpHeaders } from '../../node_modules/@angular/common/http'
import { Observable } from 'rxjs';
import { Products } from './products'
import { Product } from './basket/product';

@Injectable({
  providedIn: 'root'
})
export class ProductsService {

  url: string
  constructor(private httpsvc:HttpClient) { 
    this.url="http://localhost:8080/products"
  }

  //simple get for all products
  getAllProducts():Observable<Products[]>{
    return this.httpsvc.get<Products[]>(this.url+"/allProducts")
  }

  //fetch by category
  fetchProductsByCategory(cat_name):Observable<Products[]>{
    return this.httpsvc.get<Products[]>(this.url+"/fetchByCategory?category="+cat_name)
  }



}
