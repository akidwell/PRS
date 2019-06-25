import { Component, OnInit } from '@angular/core';
import { JsonResponse } from '@model/json-response.class';
import { PurchaseRequest } from '@model/purchase-request.class';
import { Router, ActivatedRoute } from '@angular/router';
import { PurchaseRequestService } from '@svc/purchase-request.service';
import { SystemService } from '@svc/system.service';
import { PurchaseRequestLineItem } from '@model/purchase-request-line-item.class';
import { PurchaseRequestLineItemService } from '@svc/purchase-request-line-item.service';

@Component({
  selector: 'app-purchase-request-lines',
  templateUrl: './purchase-request-lines.component.html',
  styleUrls: ['./purchase-request-lines.component.css']
})
export class PurchaseRequestLinesComponent implements OnInit {
  jr: JsonResponse;
  jr2: JsonResponse;
  pr: PurchaseRequest;
  title: string = 'Purchase Request Line Items';
  prIdStr: string;
  prliId: string;
  prlis: PurchaseRequestLineItem[];
  prli: PurchaseRequestLineItem;

  constructor(private prSvc: PurchaseRequestService,
    private prliSvc: PurchaseRequestLineItemService,
    private route: ActivatedRoute,
    private sysSvc: SystemService,
    private router: Router) { }

  ngOnInit() {
    this.route.params.subscribe(params => this.prIdStr = params['id']);
    //gets the purchaseRequest
    this.prSvc.get(this.prIdStr).subscribe(jresp => {
      this.jr = jresp;
      if (this.jr.errors == null) {
        this.pr = this.jr.data as PurchaseRequest;
      }
    });

    //gets all the prli's for a PR 
    this.prliSvc.linesForPr(this.prIdStr).subscribe(
      jresp => {
        this.jr2 = jresp;
        this.prlis = this.jr2.data as PurchaseRequestLineItem[];
      });
  }

  remove(inPrli) {
    this.prliSvc.remove(inPrli).subscribe(
      jresp => {
        this.jr = jresp;
        this.refresh();
      }
    );
  }

  refresh(){
    this.prSvc.get(this.prIdStr).subscribe(jresp => {
      this.jr = jresp;
      if (this.jr.errors == null) {
        this.pr = this.jr.data as PurchaseRequest;
      }
    });

    //gets all the prli's for a PR 
    this.prliSvc.linesForPr(this.prIdStr).subscribe(
      jresp => {
        this.jr2 = jresp;
        this.prlis = this.jr2.data as PurchaseRequestLineItem[];
      });
  }

submitForReview() {
  this.prSvc.submitForReview(this.pr).subscribe(
    jresp => {
      this.jr = jresp;
      //assuming a good call
      //no error handling yet
      this.router.navigate(['/pr/list']);
    }
  )
}
    }