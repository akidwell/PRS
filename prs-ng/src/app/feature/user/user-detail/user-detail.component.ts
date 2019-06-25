import { Component, OnInit } from '@angular/core';
import { JsonResponse } from '@model/json-response.class';
import { UserService } from '@svc/user.service';
import { User } from '@model/user.class';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-user-detail',
  templateUrl: './user-detail.component.html',
  styleUrls: ['./user-detail.component.css']
})
export class UserDetailComponent implements OnInit {
  title: string = "User Detail:"
  userIdStr: string;
  jr: JsonResponse;
  user: User;
  
  constructor(private userSvc: UserService,
              private router: Router,
              private route: ActivatedRoute) { }

  ngOnInit() {
    this.route.params.subscribe(params => this.userIdStr = params['id']);
    this.userSvc.get(this.userIdStr).subscribe(jresp =>{
      this.jr = jresp;
      this.user = this.jr.data as User;
    });
  }
remove() {
  this.userSvc.remove(this.user).subscribe(
    jresp => {
      this.jr = jresp;
      this.user = this.jr.data as User;
      this.router.navigate(['/product/list']);
    }
  );
}
}
