import { Component, OnInit } from '@angular/core';
import { JsonResponse } from '@model/json-response.class';
import { PurchaseRequest } from '@model/purchase-request.class';
import { Product } from '@model/product.class';
import { PurchaseRequestLineItem } from '@model/purchase-request-line-item.class';
import { PurchaseRequestLineItemService } from '@svc/purchase-request-line-item.service';
import { PurchaseRequestService } from '@svc/purchase-request.service';
import { ProductService } from '@svc/product.service';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-purchase-request-line-item-edit',
  templateUrl: './purchase-request-line-item-edit.component.html',
  styleUrls: ['./purchase-request-line-item-edit.component.css']
})
export class PurchaseRequestLineItemEditComponent implements OnInit {
  jr: JsonResponse;
  title: string = "Purchase Request Line Item Edit";
  pr: PurchaseRequest;
  prliIdStr: string;
  products: Product[];
  prli: PurchaseRequestLineItem = new PurchaseRequestLineItem();
  
  constructor(private prliSvc: PurchaseRequestLineItemService,
    private prSvc: PurchaseRequestService,
    private productSvc: ProductService,
    private router: Router,
    private route: ActivatedRoute) { }

  ngOnInit() {
    this.productSvc.list().subscribe(
      jresp => {
        this.jr = jresp;
        this.products = this.jr.data as Product[];
      }
    );

    this.route.params.subscribe(params =>
      this.prliIdStr = params['id']);

    this.prliSvc.get(this.prliIdStr).subscribe(
      jsrep => {
        this.jr = jsrep;
        this.prli = this.jr.data as PurchaseRequestLineItem;
        
      }
    );

  }
  edit() {
    this.prliSvc.edit(this.prli).subscribe(
      jresp => {
        this.jr = jresp;
        this.pr = this.jr.data as PurchaseRequest;
        this.router.navigate(['/purchase-request/lines/'+ this.prli.purchaseRequest.id]);
      }
    )
  }
}
