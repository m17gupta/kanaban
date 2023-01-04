package com.capstoneproject.taskdata.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND,reason = "Task Id not found ")
public class TaskIdNotFoundException extends Exception{
}
