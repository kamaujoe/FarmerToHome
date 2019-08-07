import { Category } from './category';

export interface Product {


    productId : number
    name : string
    price : number
    quantity : number
    size : string
    expiry_date : number
    
    currentCategory : Category[]

}