import { async, ComponentFixture, TestBed ,fakeAsync,tick} from '@angular/core/testing';
import{User} from './../User'

import { LoginComponent } from './login.component';
import { AuthenticationService } from 'src/app/modules/authentication/authentication.service';
import { Router } from '@angular/router';
import { Location } from '@angular/common';
import { Observable , of} from 'rxjs'
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { MatFormFieldModule, MatInputModule, MatButtonModule,MatIcon,MatSnackBarModule } from '@angular/material';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {RouterTestingModule} from '@angular/router/testing';
import {By} from '@angular/platform-browser'

const testConfig={

    userdata:{
      firstName:'test',
      lastName:'testlast',
      userid:'testuser',
      password:'testpass'

    }
  }


describe('LoginComponent', () => {
  let component: LoginComponent;
  let fixture: ComponentFixture<LoginComponent>;
  let authService: AuthenticationService;
  let spyUser:any;
  let routes:Router;
  let location:Location;

  class AuthServiceStub{
    currentUser:any;
    constructor(){

    }

    loginUser(credentials){
      debugger;
      if(credentials.userid == testConfig.userdata.userid){
        return of(credentials.userid);
      }
      return of(false);
    }
  }

  class dummy{

  }

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ LoginComponent ],

      imports:[FormsModule,HttpClientModule,MatFormFieldModule,MatInputModule,MatButtonModule,BrowserAnimationsModule,RouterTestingModule.withRoutes([
        {path:'',component:dummy}
      ])],
      providers:[{provide:AuthenticationService,useClass:AuthServiceStub}],
    })
    .compileComponents();
  }));

  beforeEach(() => {
    routes=TestBed.get(Router)
    fixture = TestBed.createComponent(LoginComponent);
    location= TestBed.get(location)
    component = fixture.componentInstance;
    fixture.detectChanges();
    fixture.debugElement.injector.get(AuthenticationService)
  });

  

});