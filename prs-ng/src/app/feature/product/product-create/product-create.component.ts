import { Component, OnInit } from '@angular/core';
import { JsonResponse } from '@model/json-response.class';
import { Product } from '@model/product.class';
import { ProductService } from '@svc/product.service';
import { Router } from '@angular/router';
import { VendorService } from '@svc/vendor.service';
import { Vendor } from '@model/vendor.class';

@Component({
  selector: 'app-product-create',
  templateUrl: './product-create.component.html',
  styleUrls: ['./product-create.component.css']
})
export class ProductCreateComponent implements OnInit {
  title: string = "Product Create";
  jr: JsonResponse;
  product: Product = new Product();
  vendors: Vendor[];

  constructor(private productsvc: ProductService,
              private vendorsvc: VendorService,
              private router: Router) {

  }

  ngOnInit() {
    this.vendorsvc.list().subscribe(
      jresp => {
        this.jr = jresp;
        this.vendors = this.jr.data as Vendor[];
      }
    );
    }

  create() {
    this.productsvc.create(this.product).subscribe(
      jresp => {
        this.jr = jresp;
        //assuming a good call
        //no error handling yet
        this.router.navigate(['/product/list']);
      }
    )
  }
}