import { Component, OnInit } from '@angular/core';
import {User} from './../User';
import {AuthenticationService} from './../authentication.service';
import{Router} from '@angular/router';

@Component({
  selector: 'auth-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  isAdmin:boolean;
  newUser:User;
  constructor(private authService:AuthenticationService, private router:Router) { 
    this.newUser= new User();
    this.isAdmin =false;
  }

  ngOnInit() {
  }

  loginUser(){
    this.authService.loginUser(this.newUser).subscribe((data)=>{
      if(data['token']){
        this.authService.setToken(data['token']);
        //test login token decode
        this.isAdmin= this.authService.isAdmin();
        this.isAdmin= this.authService.isAdmin();
        if(this.isAdmin){
          this.router.navigate(['news/allnews']);
        }else{
          
          this.router.navigate(['/news/appnews']);
        }
       
      }
    })
  }

}