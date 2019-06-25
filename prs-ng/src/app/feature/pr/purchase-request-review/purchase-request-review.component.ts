import { Component, OnInit } from '@angular/core';
import { JsonResponse } from '@model/json-response.class';
import { PurchaseRequest } from '@model/purchase-request.class';
import { PurchaseRequestService } from '@svc/purchase-request.service';
import { SystemService } from '@svc/system.service';
import { User } from '@model/user.class';
import { Route, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-purchase-request-review',
  templateUrl: './purchase-request-review.component.html',
  styleUrls: ['./purchase-request-review.component.css']
})
export class PurchaseRequestReviewComponent implements OnInit {
  title: string = "Purchase Request Review";
  jr: JsonResponse;
  prs: PurchaseRequest[];
  user: User;

  constructor(private prSvc: PurchaseRequestService,
    private sysSvc: SystemService) { }

    ngOnInit() {
      if (this.sysSvc.data.user.loggedIn) {
        this.user = this.sysSvc.data.user.instance;
        this.prSvc.listForReview(this.user.id).subscribe(
          jresp => {
            this.jr = jresp;
            if (this.jr.errors == null) {
              this.prs = this.jr.data as PurchaseRequest[];
            } else {
              console.log("error getting purchase requests");
            }
          }
        )};
    }
  }
  