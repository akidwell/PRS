import { Component, OnInit } from '@angular/core';
import { PurchaseRequestService } from '@svc/purchase-request.service';
import { ActivatedRoute, Router } from '@angular/router';
import { JsonResponse } from '@model/json-response.class';
import { PurchaseRequestLineItem } from '@model/purchase-request-line-item.class';
import { PurchaseRequestLineItemService } from '@svc/purchase-request-line-item.service';
import { ProductService } from '@svc/product.service';
import { Product } from '@model/product.class';
import { PurchaseRequest } from '@model/purchase-request.class';

@Component({
  selector: 'app-purchase-request-line-item-create',
  templateUrl: './purchase-request-line-item-create.component.html',
  styleUrls: ['./purchase-request-line-item-create.component.css']
})
export class PurchaseRequestLineItemCreateComponent implements OnInit {
  jr: JsonResponse;
  title: string = "Purchase Request Line Item Create";
  pr: PurchaseRequest;
  prIdStr: string;
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
      });

    this.route.params.subscribe(params =>
      this.prIdStr = params['id']);

    this.prSvc.get(this.prIdStr).subscribe(
      jsrep => {
        this.jr = jsrep;
        this.pr = this.jr.data as PurchaseRequest;
        this.prli.purchaseRequest = this.pr;
        this.title = `Purchase-Request-Line-Item-Create - PR ID: ${this.pr.id}`;
      });

  }
  create() {
    this.prliSvc.create(this.prli).subscribe(
      jresp => {
        this.jr = jresp;
        this.router.navigate(['/purchase-request/lines/' + this.prIdStr]);
      }
    )
  }
}


