import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { emailValidate } from 'emailValidate';
import { passwordValidate } from 'passwordValidate';
import { AuthenticateService } from '../authenticate.service';
import {MatSnackBar} from '@angular/material/snack-bar';
import { FormBuilder } from '@angular/forms';

@Component({
  selector: 'app-sign-up-dialog',
  templateUrl: './sign-up-dialog.component.html',
  styleUrls: ['./sign-up-dialog.component.css']
})
export class SignUpDialogComponent implements OnInit {
  snackBar: any;
  methodError: any;


  constructor(private authService:AuthenticateService,
    private router:Router,private snackbar: MatSnackBar,private formBuilder: FormBuilder    ) { }

  ngOnInit(): void {
  }


  signupForm = this.formBuilder.group({
    employeeName: new FormControl('', [Validators.required, Validators.maxLength(20)]),
    emailId: new FormControl('', [Validators.required, emailValidate()]),
    password: new FormControl('', [Validators.required, passwordValidate()]),
    confirmPassword: new FormControl('', [Validators.required]),
    role: new FormControl('', [Validators.required])

  },
    {
      validators: this.MustMatch('password', 'confirmPassword')
    });
  MustMatch(controlName: string, matchingControlName: string) {
    return (formGroup: FormGroup) => {
      const control = formGroup.controls[controlName];
      const matchingControl = formGroup.controls[matchingControlName];
      if (matchingControl.errors && !matchingControl.errors['MustMatch']) {
        return;
      }
      if (control.value !== matchingControl.value) {
        matchingControl.setErrors({ MustMatch: true });
      } else {
        matchingControl.setErrors(null);
      }
    }
  }

  get employeeName(){
    return this.signupForm.get('employeeName');
  }

  get emailId(){
    return this.signupForm.get('emailId');
  }

  get password(){
    return this.signupForm.get('password');
  }
  get role(){
    return this.signupForm.get('role');
  }

  
  get phoneNo(){
    return this.signupForm.get('phoneNo');
  }

  signup(){
     this.authService.signUp(this.signupForm.value).subscribe(
      response=>{
       
        console.log(response);
        this.snackbar.open("succesfully registered", "close")
        this.router.navigateByUrl("/login")
      },
      error => {
        this.methodError = error;
        console.log(this.methodError.error.message);
        if (this.methodError.error.message.includes("Conflict"))
          this.snackbar.open("User already exists", "close");
        this.signupForm.reset();

      }
     )
  }
}
