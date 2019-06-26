import { Component, OnInit } from '@angular/core';
import { MenuItem } from '@model/menu-item.class';
import { SystemService } from '@svc/system.service';
import { Router } from '@angular/router';
import { UseExistingWebDriver } from 'protractor/built/driverProviders';
import { User } from '@model/user.class';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css']
})
export class MenuComponent implements OnInit {
  menuItems: MenuItem[]= [
  new MenuItem("Home","/home","This is the home menu item"),
  new MenuItem("User","/user/list","This is the user menu item"),
  new MenuItem("Vendor","/vendor/list","This is the vendor menu item"),
  new MenuItem("Product","/product/list","This is the product menu item"),
  new MenuItem("Request","/pr/list","This is the pr menu item"),
  new MenuItem("Review","/pr/review","This is the pr review menu item"),
  new MenuItem("Login","/user/login","This is the login menu item"),
  new MenuItem("About","/about","This is the about menu item")
  ];
  loggedInUser: User;

  constructor(private sysSvc: SystemService,
              private router: Router) { }

  ngOnInit()   {
    this.sysSvc.data.user.loggedIn;
    this.loggedInUser = this.sysSvc.data.user.instance;
  }
  }

