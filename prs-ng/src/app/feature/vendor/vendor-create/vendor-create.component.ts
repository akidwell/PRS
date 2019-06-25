import { Component, OnInit } from '@angular/core';
import { JsonResponse } from '@model/json-response.class';
import { Vendor } from '@model/vendor.class';
import { VendorService } from '@svc/vendor.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-vendor-create',
  templateUrl: './vendor-create.component.html',
  styleUrls: ['./vendor-create.component.css']
})
export class VendorCreateComponent implements OnInit {
  title: string = "Vendor Create";
  jr: JsonResponse;
  vendor: Vendor = new Vendor();

  constructor(private vendorSvc : VendorService, 
              private router : Router) { }

  ngOnInit() {
  }
  create(){
    this.vendorSvc.create(this.vendor)
    .subscribe(
      jresp=>{
        this.jr = jresp;
        //assuming a good call- see user list component ts for error handling?
        // go back to user list
        this.router.navigate(['/vendor/list']);
      });
  }
}
