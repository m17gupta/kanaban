package com.capstoneproject.taskdata.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code= HttpStatus.CONFLICT,reason="employee already exists ")
public class EmployeeAlreadyExistsException extends Exception{
}
