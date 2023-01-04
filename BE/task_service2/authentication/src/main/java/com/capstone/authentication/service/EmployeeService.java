package com.capstone.authentication.service;

import com.capstone.authentication.domain.EmployeeDetails;
import com.capstone.authentication.exception.EmployeeAlreadyExistsException;
import com.capstone.authentication.exception.EmployeeNotFoundException;

public interface EmployeeService {
    public abstract EmployeeDetails addEmployee(EmployeeDetails employeeDetails) throws EmployeeAlreadyExistsException;
    public abstract EmployeeDetails loginCheck(String emailId,String password) throws EmployeeNotFoundException;
}
