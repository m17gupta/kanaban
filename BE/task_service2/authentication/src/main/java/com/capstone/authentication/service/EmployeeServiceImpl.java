package com.capstone.authentication.service;

import com.capstone.authentication.domain.EmployeeDetails;
import com.capstone.authentication.exception.EmployeeAlreadyExistsException;
import com.capstone.authentication.exception.EmployeeNotFoundException;
import com.capstone.authentication.rabbitMQ.EmailDetails;
import com.capstone.authentication.rabbitMQ.Producer;
import com.capstone.authentication.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private Producer producer;
    @Override
    public EmployeeDetails addEmployee(EmployeeDetails employeeDetails) throws EmployeeAlreadyExistsException {
        if(employeeRepository.findById(employeeDetails.getEmailId()).isPresent()){
            throw new EmployeeAlreadyExistsException();
        }else {
            EmailDetails emailDetails=new EmailDetails();
            emailDetails.setRecipient(employeeDetails.getEmailId());
            emailDetails.setSubject("Registration successfull");
            emailDetails.setMsgBody("Welcome to Kanban Board!!! Have A nice day!!");
            producer.sendDTOtoQueue(emailDetails);
            return employeeRepository.save(employeeDetails);
        }
        }



    @Override
    public EmployeeDetails loginCheck(String emailId, String password) throws EmployeeNotFoundException {
        if(employeeRepository.findById(emailId).isPresent()){
            EmployeeDetails result=employeeRepository.findById(emailId).get();
            if(result.getPassword().equals(password)){
                result.setPassword("");
                return result;
            }
            throw new EmployeeNotFoundException();
        }

        throw new EmployeeNotFoundException();
    }

}
