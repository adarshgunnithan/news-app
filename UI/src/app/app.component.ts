import { Component ,OnInit} from '@angular/core';
import {AuthenticationService} from '../app/modules/authentication/authentication.service';
import {Router} from '@angular/router';
@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit{
  title = 'News-App-boilerplate-java';
  isAdmin:boolean;
  isMenuRequired:boolean;

  constructor(public authService :AuthenticationService,private routes: Router){
    this.isAdmin=false;
    this.isMenuRequired=false;
  }
  Logout(){
    console.log("log out action triggered");
    this.authService.deleteToken();
    this.routes.navigate(['/login']);
  }
  ngOnInit(){
    // console.log("intalizinggggg");
    // const token = this.authService.getToken();
    // console.log("token is ", token);
    
    // if(token != null){
    //   this.isMenuRequired=true;
    //   console.log("menu required");
    //   this.isAdmin=this.authService.isAdmin();
    //   console.log("user type in app component is Admin?", this.isAdmin);
    // }
    
    
  }


  
}
