import { PurchaseRequest } from './purchase-request.class';
import { Product } from './product.class';

export class PurchaseRequestLineItem {


	id: number;
	purchaseRequest: PurchaseRequest;
    product: Product;
    quantity: number;
    
    constructor (id: number = 0, pr: PurchaseRequest=null, product: Product = null, quantity: number =0){
        
    }
}