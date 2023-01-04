import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class AuthenticateService {

  constructor( private httpCilent:HttpClient) { }

   authenticationFlag:boolean=false;

  baseurl="http://localhost:8888/employee-details/v1";

  baseUrl2="http://localhost:8888/employee-app/v1"

  signUp(signupdata:any){
    return this.httpCilent.post(this.baseurl+"/add-user",signupdata);
  }

  login(login:any){
    return this.httpCilent.post(this.baseUrl2+"/employee-login",login);
    
  }


}
