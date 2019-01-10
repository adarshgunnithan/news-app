import { Component, OnInit } from '@angular/core';
import {User} from './../User';
import {AuthenticationService} from './../authentication.service';
import{Router} from '@angular/router';
import {MatSnackBar} from '@angular/material/snack-bar';
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA} from '@angular/material';
import {MatDialogModule} from '@angular/material/dialog';
@Component({
  selector: 'auth-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  newUser:User;
  constructor(private authService:AuthenticationService, private router:Router,private matSnackBar :MatSnackBar) { 
    this.newUser= new User();
  }

  ngOnInit() {
  }

  registerUser(){
    if((this.newUser.firstName != null) && (this.newUser.lastName != null)&& (this.newUser.userId != null)&&(this.newUser.password != null)){
      this.authService.registerUser(this.newUser).subscribe((data)=>{
        console.log(data);
      this.matSnackBar.open('Registered user successfully!','',{duration:1000})
      this.router.navigate(['/login']);
    }, (err) => {
      console.log("error message",err.error);
      this.matSnackBar.open(err.error,'',{duration:1000})
    })
  }

}
  resetInput(){
    this.newUser=null;
  }

}