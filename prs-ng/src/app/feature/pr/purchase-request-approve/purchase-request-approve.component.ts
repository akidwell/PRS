import { Component, OnInit } from '@angular/core';
import { JsonResponse } from '@model/json-response.class';
import { PurchaseRequest } from '@model/purchase-request.class';
import { PurchaseRequestService } from '@svc/purchase-request.service';
import { SystemService } from '@svc/system.service';
import { Router, ActivatedRoute } from '@angular/router';
import { PurchaseRequestLineItemService } from '@svc/purchase-request-line-item.service';
import { PurchaseRequestLineItem } from '@model/purchase-request-line-item.class';

@Component({
  selector: 'app-purchase-request-approve',
  templateUrl: './purchase-request-approve.component.html',
  styleUrls: ['./purchase-request-approve.component.css']
})
export class PurchaseRequestApproveComponent implements OnInit {
  title: string = "Purchase Request Approve/Reject";
  jr: JsonResponse;
  jr2: JsonResponse;
  pr: PurchaseRequest;
  prId: string;
  prlis: PurchaseRequestLineItem[];

  constructor(private prSvc: PurchaseRequestService,
    private prliService: PurchaseRequestLineItemService,
    private sysSvc: SystemService,
    private router: Router,
    private route: ActivatedRoute) { }

  ngOnInit() {
    this.route.params.subscribe(params => this.prId = params['id']);
    this.prSvc.get(this.prId).subscribe(jresp =>{
      this.jr = jresp;
      this.pr = this.jr.data as PurchaseRequest;
      this.pr.reasonForRejection="";
    });

  this.prliService.linesForPr(this.prId).subscribe(
    jresp => {
      this.jr2= jresp;
      this.prlis = this.jr2.data as PurchaseRequestLineItem[];
    });
}

  approve() {
    this.prSvc.approve(this.pr).subscribe(
      jresp => {
        this.jr = jresp;
        //assuming a good call
        //no error handling yet
        this.router.navigate(['/pr/review']);
      }
    )
  }

  reject() {
    this.prSvc.reject(this.pr).subscribe(
      jresp => {
        this.jr = jresp;
        //assuming a good call
        //no error handling yet
        this.router.navigate(['/pr/review']);
      }
    )
  }

}
