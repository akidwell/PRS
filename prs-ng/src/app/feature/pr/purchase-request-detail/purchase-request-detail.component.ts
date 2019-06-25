import { Component, OnInit } from '@angular/core';
import { PurchaseRequest } from '@model/purchase-request.class';
import { JsonResponse } from '@model/json-response.class';
import { PurchaseRequestService } from '@svc/purchase-request.service';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-purchase-request-detail',
  templateUrl: './purchase-request-detail.component.html',
  styleUrls: ['./purchase-request-detail.component.css']
})
export class PurchaseRequestDetailComponent implements OnInit {
  jr: JsonResponse;
  title: string = 'Purchase Request Detail';
  prIdStr: string;
  pr: PurchaseRequest;

  constructor(private prsvc: PurchaseRequestService,
    private router: Router,
    private route: ActivatedRoute) { }

    ngOnInit() {
      this.route.params.subscribe(params => this.prIdStr = params['id']);
      this.prsvc.get(this.prIdStr).subscribe(jresp =>{
        this.jr = jresp;
        this.pr = this.jr.data as PurchaseRequest;
      });
    }
  remove() {
    this.prsvc.remove(this.pr).subscribe(
      jresp => {
        this.jr = jresp;
        this.pr = this.jr.data as PurchaseRequest;
        this.router.navigate(['/pr/list']);
      }
    );
  }
  }