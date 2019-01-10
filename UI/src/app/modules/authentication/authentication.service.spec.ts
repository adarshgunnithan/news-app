import { TestBed ,inject,  fakeAsync} from '@angular/core/testing';

import { AuthenticationService } from './authentication.service';
import { HttpClientModule ,HttpClient,HttpEvent,HttpEventType} from '@angular/common/http';
import {HttpClientTestingModule} from '@angular/common/http/testing';
import { HttpTestingController } from '@angular/common/http/testing';

const testConfig={

  addUser:{
    positive:{
      firstName:'test',
      lastName:'testlast',
      userid:'testuser',
      password:'testpass'

    }
  },
  loginUser:{
    positive:{
      userid:'testuser',
      password:'testpass'

    }
  }
}

describe('AuthenticationService', () => {

  let authService: AuthenticationService;

  beforeEach(() =>{
  TestBed.configureTestingModule({
    imports:[HttpClientModule,HttpClientTestingModule],
    providers:[AuthenticationService]
  });
  authService=TestBed.get(AuthenticationService)
  });

  it('should create the authentication service',
   inject([AuthenticationService],(service:AuthenticationService)=>{
     expect(service).toBeTruthy();
   }));

   it('should register user data',fakeAsync(()=>{

    let data = testConfig.addUser.positive;

    inject([AuthenticationService,HttpClientTestingModule],(backend:HttpTestingController)=>{
      const mockReq=backend.expectOne(authService.springEndpoint);
      expect(mockReq.request.url).toEqual(authService.springEndpoint,'request service url should match with json server api url');
      expect(mockReq.request.method).toBe('POST','should handle requested method type');

      mockReq.flush(data);
      backend.verify();
     });

	authService.registerUser(data).subscribe((res:any)=>{
      expect(res).toBeDefined();
      expect(res._body).toBe(data,'data should be same');

});

})) ;




   it('should login user',fakeAsync(()=>{
debugger;
    let userdata = testConfig.loginUser.positive;

    inject([AuthenticationService,HttpClientTestingModule],(backend:HttpTestingController)=>{
      const mockReq=backend.expectOne(authService.springEndpoint);
      expect(mockReq.request.url).toEqual(authService.springEndpoint,'request service url should match with json server api url');
      expect(mockReq.request.method).toBe('POST','should handle requested method type');

      mockReq.flush(userdata);
      backend.verify();
     });
 debugger;
	authService.loginUser(userdata).subscribe((res:any)=>{
      expect(res).toBeDefined();
      expect(res._body).toBe(userdata,'data should be same');

});

})) ;

});