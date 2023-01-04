import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { JwtHelperService } from '@auth0/angular-jwt';
import { AuthenticateService } from '../authenticate.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  taskError: any;
  methodError: any;

  constructor(private Authservice:AuthenticateService, private router:Router,private snackbar: MatSnackBar) { }

  ngOnInit(): void {
  }

  
  loginForm = new FormGroup({
    emailId: new FormControl('',[Validators.required]),
    password: new FormControl('',[Validators.required])
  })

    
  get emailId(){
    return this.loginForm.get('emailId');
  }
    
  get password(){
    return this.loginForm.get('password');
  }


  helper= new JwtHelperService();
 
  email:any;
  name:any;
  role:any;
   responseToken:any;
  login(){
    console.log("enter")
   this.Authservice.login(this.loginForm.value).subscribe(
    
    responce=>{
      console.log(responce);
      
      this.responseToken=responce;
      
      localStorage.setItem('jwt',this.responseToken.token);
      console.log(this.responseToken);
      const decodeToken=this.helper.decodeToken(this.responseToken.token)
        console.log(decodeToken);
        this.email=decodeToken.userObject.emailId;
        this.name=decodeToken.userObject.employeeName;
        this.role=decodeToken.userObject.role;
        this.Authservice.authenticationFlag=true;
        localStorage.setItem('email',this.email)
        localStorage.setItem('name',this.name)
        console.log(this.email);
        console.log(this.name);
        
          // alert("login successfully")
          this.snackbar.open("Login Sucessfully", "close")
          if(this.role.match('team Member')){
            this.router.navigateByUrl("/teamdashboard")
          }
          else{
            this.router.navigateByUrl("/kanban");
          }
           
    },
    error => {
      this.methodError = error;
      console.log(this.methodError.error.message);
      if (this.methodError.error.message.includes("NOT_FOUND"))
        this.snackbar.open("Employee Not found", "close");
      this.loginForm.reset();

    }
   )
   
  }

}
