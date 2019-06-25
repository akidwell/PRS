import { Component, OnInit } from '@angular/core';
import { JsonResponse } from '@model/json-response.class';
import { PurchaseRequest } from '@model/purchase-request.class';
import { User } from '@model/user.class';
import { PurchaseRequestService } from '@svc/purchase-request.service';
import { Router, ActivatedRoute } from '@angular/router';
import { SystemService } from '@svc/system.service';

@Component({
  selector: 'app-purchase-request-edit',
  templateUrl: './purchase-request-edit.component.html',
  styleUrls: ['./purchase-request-edit.component.css']
})
export class PurchaseRequestEditComponent implements OnInit {
  title: string = "Purchase Request Edit";
  jr: JsonResponse;
  pr: PurchaseRequest;
  prIdStr: string;
  users: User[];
  
  constructor(private prSvc: PurchaseRequestService,
    private sysSvc: SystemService,
    private router: Router,
    private route: ActivatedRoute) { }

  ngOnInit() {
    this.route.params.subscribe(params => this.prIdStr = params['id']);
    this.prSvc.get(this.prIdStr).subscribe(jresp => {
      this.jr = jresp;
      this.pr = this.jr.data as PurchaseRequest;
    });
    if(this.sysSvc.data.user.loggedIn) {
      this.pr.user = this.sysSvc.data.user.instance;
    } else {
      console.log("user not logged in");
    }
    };

  edit(){
    this.prSvc.edit(this.pr).subscribe(
      jresp => {
        this.jr = jresp;
        //next line is not necessary. don't need edited product back since we're going straight to product list page
        this.pr = this.jr.data as PurchaseRequest;
        this.router.navigate(['/pr/list']);
       });
  }

}
