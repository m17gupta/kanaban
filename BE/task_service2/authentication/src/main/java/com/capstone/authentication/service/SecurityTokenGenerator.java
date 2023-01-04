package com.capstone.authentication.service;



import com.capstone.authentication.domain.EmployeeDetails;

import java.util.Map;

public interface SecurityTokenGenerator {
    public abstract Map<String, String> generateToken(EmployeeDetails employeeDetails);
}
