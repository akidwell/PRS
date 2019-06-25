import { Component, OnInit } from '@angular/core';
import { JsonResponse } from '@model/json-response.class';
import { PurchaseRequest } from '@model/purchase-request.class';
import { User } from '@model/user.class';
import { PurchaseRequestService } from '@svc/purchase-request.service';
import { UserService } from '@svc/user.service';
import { Router } from '@angular/router';
import { SystemService } from '@svc/system.service';

@Component({
  selector: 'app-purchase-request-create',
  templateUrl: './purchase-request-create.component.html',
  styleUrls: ['./purchase-request-create.component.css']
})
export class PurchaseRequestCreateComponent implements OnInit {
  title: string = "Purchase Request Create";
  jr: JsonResponse;
  pr: PurchaseRequest = new PurchaseRequest();

  constructor(private prSvc: PurchaseRequestService,
    private sysSvc: SystemService,
    private router: Router) { }

  ngOnInit() {
    if (this.sysSvc.data.user.loggedIn) {
      this.pr.user = this.sysSvc.data.user.instance;
    } else {
      console.log("user not logged in");
    }
  }
  create() {
    this.prSvc.create(this.pr).subscribe(
      jresp => {
        this.jr = jresp;
        if (this.jr.errors == null) {
          this.router.navigate(['/pr/list']);
          console.log(this.pr);
        } else {
          console.log("Error getting purchase request");
          // TODO: Implement error handling
        }
      }
    );
  }

}

