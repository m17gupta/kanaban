package com.capstoneproject.taskdata.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code= HttpStatus.TEMPORARY_REDIRECT,reason = "Team Member Task Limit Exceeds")
public class TasklimitExceed extends Exception{
}
