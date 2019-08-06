import { Category } from './category';

export interface Product {


    productId : number
    productName : string
    price : number
    quantity : number
    size : string

    expiryDate : number
    currentCategory : Category

}