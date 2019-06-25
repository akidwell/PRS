import { Component, OnInit } from '@angular/core';
import { JsonResponse } from '@model/json-response.class';
import { PurchaseRequest } from '@model/purchase-request.class';
import { PurchaseRequestService } from '@svc/purchase-request.service';

@Component({
  selector: 'app-purchase-request-list',
  templateUrl: './purchase-request-list.component.html',
  styleUrls: ['./purchase-request-list.component.css']
})
export class PurchaseRequestListComponent implements OnInit {
  jr: JsonResponse;
  pr: PurchaseRequest[];
  title: string = 'Purchase Request List';


  constructor(private prSvc: PurchaseRequestService) { }

  ngOnInit() {
      this.prSvc.list().subscribe(
        jresp => {
          this.jr = jresp;
          if (this.jr.errors == null) {
            this.pr = this.jr.data as PurchaseRequest[];
          } else {
            console.log("error getting purchase requests");
            //to do - implement error handling???s
          }
        }
      )
    }
  }


