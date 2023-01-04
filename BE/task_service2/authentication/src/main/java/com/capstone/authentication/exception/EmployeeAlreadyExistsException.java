package com.capstone.authentication.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code= HttpStatus.CONFLICT,reason = "email id already exists")
public class EmployeeAlreadyExistsException extends Exception{
}
