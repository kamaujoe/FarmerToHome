import { Category } from './basket/category';

export interface Products {
    productId: number
    name: string
    price: number
    quantity: number
    // expiryDate: number;
    category: Category
    size: string
}
