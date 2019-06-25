import { Component, OnInit } from '@angular/core';
import { JsonResponse } from '@model/json-response.class';
import { Product } from '@model/product.class';
import { ProductService } from '@svc/product.service';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-product-detail',
  templateUrl: './product-detail.component.html',
  styleUrls: ['./product-detail.component.css']
})
export class ProductDetailComponent implements OnInit {
  jr: JsonResponse;
  title: string = 'Product Detail';
  userIdStr: string;
  product: Product;

  constructor(private productsvc: ProductService,
              private router: Router,
              private route: ActivatedRoute) { }

  ngOnInit() {
    this.route.params.subscribe(params => this.userIdStr = params['id']);
    this.productsvc.get(this.userIdStr).subscribe(jresp =>{
      this.jr = jresp;
      this.product = this.jr.data as Product;
    });
  }
remove() {
  this.productsvc.remove(this.product).subscribe(
    jresp => {
      this.jr = jresp;
      this.product = this.jr.data as Product;
      this.router.navigate(['/product/list']);
    }
  );
}
}
