import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class SystemService {
  data = {
    about: 'SystemService',
    user: {
      loggedIn: false,
      instance: null
    }
  }

  constructor() { }
}
