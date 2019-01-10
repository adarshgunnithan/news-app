import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import * as jwt_decode from 'jwt-decode';
//npm install --save jwt-decode
export const TOKEN_NAME:string='jwt_token';

// @Injectable({
//   providedIn: 'root'
// })
@Injectable()
export class AuthenticationService {

  mockApi:'http://example/test';
  springEndpoint:string;
  token:string;

  constructor(private httpClient:HttpClient) { 

    this.springEndpoint='http://localhost:8089/api/userservice';
  }

  registerUser(newUser){
    debugger;
    const url = this.springEndpoint+"/register";
    return this.httpClient.post(url,newUser,{responseType:'text'});
  }

  loginUser(newUser){
    const url = this.springEndpoint+"/login";
    return this.httpClient.post(url,newUser);
  }

  setToken(token:string){
    return localStorage.setItem(TOKEN_NAME,token);
  }

  getToken(){
    return localStorage.getItem(TOKEN_NAME);
  }

  deleteToken(){
    return localStorage.removeItem(TOKEN_NAME);
  }

  getTokenExpirationDate(token:string):Date{
    const decoded = jwt_decode(token);
    if(decoded.exp ==undefined) return null;
    const date = new Date(0);
    date.setUTCSeconds(decoded.exp);
    return date;
  }

  isTokenExpired(token?:string):boolean{
    if(!token) token=this.getToken();
    if(!token) return true;
    const date =this.getTokenExpirationDate(token);
    if(date==undefined || date==null) return false;
    return !(date.valueOf() > new Date().valueOf());
  }

  isAdmin():boolean{
    const token= this.getToken();
    if(token != null){
    const decoded = jwt_decode(token);
    const role=decoded.roles.scopes;
    
    if(role=='ROLE_ADMIN'){
      return true;
     
    }
    return false
  }else{
    return false
  }
    
  }

  isLoggedIn():boolean{
    const token= this.getToken();
    if(token == null){
      return false;
  }else{
      return true;
  }
}
}