package com.capstoneproject.taskdata.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code= HttpStatus.CONFLICT,reason = "project already exists with this id")
public class ProjectAlreadyExistsException extends Exception{
}
