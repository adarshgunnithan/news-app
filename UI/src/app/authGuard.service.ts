import { Injectable,OnInit } from '@angular/core';


import {Observable} from 'rxjs';
import {Router,ActivatedRoute } from '@angular/router';

import { CanActivate } from '@angular/router/src/interfaces';
import { AuthenticationService } from '../app/modules/authentication/authentication.service';



@Injectable()
export class AuthGuardService implements CanActivate {
    
routeparam :string;

  constructor(private router:Router,private authSer: AuthenticationService,private activatedRouter :ActivatedRoute) {

  }

  canActivate(){
      if(!this.authSer.isTokenExpired()){
        
        //   if(this.authSer.isAdmin()){
        //         if((this.router.url =='/news/watchlist') ||(this.router.url =='/news/search') ){
        //             return false;
        //         }
        //   }else{
        //       console.log("its not admin")
        //       if(this.router.url =='/news/allnews'){
        //           return false;
        //       }
        //   }

          return true;
      }
      this.router.navigate(['/login']);
      return false;
  }

  //router.url

  appnews
  
}