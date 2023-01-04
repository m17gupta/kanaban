package com.capstone.authentication.controller;

import com.capstone.authentication.domain.EmployeeDetails;
import com.capstone.authentication.exception.EmployeeAlreadyExistsException;
import com.capstone.authentication.exception.EmployeeNotFoundException;
import com.capstone.authentication.service.EmployeeService;
import com.capstone.authentication.service.SecurityTokenGenerator;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/employee-app/v1")


public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private SecurityTokenGenerator securityTokenGenerator;

    //    http://localhost:8888/employee-app/v1/employee-add
    @PostMapping("/employee-add")
    public ResponseEntity<?> addEmployee(@RequestBody EmployeeDetails employeeDetails) throws EmployeeAlreadyExistsException {
        try {
            EmployeeDetails emp = employeeService.addEmployee(employeeDetails);
            return new ResponseEntity<>(emp, HttpStatus.OK);

        } catch (EmployeeAlreadyExistsException ex) {
            throw new EmployeeAlreadyExistsException();
        }

    }

    //    http://localhost:8888/employee-app/v1/employee-login
    @PostMapping("/employee-login")
    public ResponseEntity<?> loginCheck(@RequestBody EmployeeDetails employeeDetails) throws EmployeeNotFoundException, InterruptedException {

//        Thread.sleep(4000);
        try {
            EmployeeDetails employeeDetails1 = employeeService.loginCheck(employeeDetails.getEmailId(), employeeDetails.getPassword());


            if (employeeDetails1 != null) { //authencation success
//            return new ResponseEntity<>(result,HttpStatus.Ok);
//            get jwt from jwtservice method by passing result oject
                Map<String, String> Key = securityTokenGenerator.generateToken(employeeDetails1);
                return new ResponseEntity<>(Key, HttpStatus.OK);
            } else { //if authenication fails
                return new ResponseEntity<>("Authenication fails", HttpStatus.NOT_FOUND);


            }

        } catch (EmployeeNotFoundException e) {
            throw new EmployeeNotFoundException();
        }
    }

    public ResponseEntity<?> logincheckfallback(@RequestBody EmployeeDetails employeeDetails){
        return new ResponseEntity<>("login service busy,kindly try again",HttpStatus.OK);
    }
}
