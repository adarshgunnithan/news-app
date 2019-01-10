import { Injectable } from '@angular/core';


import { Observable } from 'rxjs';

import {AuthenticationService} from '../authentication/authentication.service';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor
} from '@angular/common/http';

@Injectable()
export class TokenInterceptor implements HttpInterceptor {


  constructor(private auth: AuthenticationService) {
console.log();
  }

  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    debugger;
    request = request.clone({
     
      headers: request.headers.set('Authorization', 'Bearer ' + `${this.auth.getToken()}`)
    });
    return next.handle(request);
  }
}