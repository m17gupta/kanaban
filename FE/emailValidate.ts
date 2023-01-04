import { AbstractControl, ValidationErrors, ValidatorFn } from "@angular/forms";

export function emailValidate():ValidatorFn{
    
    // return type might be null or validate
    return (control:AbstractControl):ValidationErrors | null =>{       
        
        const regex = /^[a-z0-9._%+-]+@[a-z0-9.-]+.[a-z]{2,4}$/
        if( regex.test(control.value)){
            return null;
        }
        else{
            return {emailValidate:{value:control.value}};
        }
    }    
}