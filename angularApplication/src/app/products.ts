import { Category } from './basket/category';

export interface Products {
    productId: number
    product_name: string
    price: number
    quantity: number
    expiryDate: number;
    currentCategory: Category[]
    size: string
}
