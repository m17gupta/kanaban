package com.capstoneproject.taskdata.controller;

import com.capstoneproject.taskdata.domain.Column;
import com.capstoneproject.taskdata.exception.ColumnAlreadyExistsException;
import com.capstoneproject.taskdata.exception.ColumnNotFoundException;
import com.capstoneproject.taskdata.exception.EmployeeNotFoundException;
import com.capstoneproject.taskdata.exception.ProjectNotFoundException;
import com.capstoneproject.taskdata.service.EmployeeDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employee-details/v1")
public class ColumnController {

  @Autowired
    private EmployeeDataService employeeDataService;
    // http://localhost:9999/employee-details/v1/add-column/emailiD/projectId
    @PostMapping("/add-column/{emailId}/{projectId}")
    public ResponseEntity<?> addColumn(@PathVariable String emailId, @PathVariable String projectId, @RequestBody Column column) throws ProjectNotFoundException, ColumnAlreadyExistsException {
        return new ResponseEntity<>(employeeDataService.addColumn(emailId,projectId,column), HttpStatus.OK);
    }


    //    http://localhost:9999/employee-details/v1/get-column/emaild/projectId
    @GetMapping("/get-column/{emailid}/{projectId}")
    public ResponseEntity<?> getColumn(@PathVariable String emailid, @PathVariable String projectId) throws ProjectNotFoundException, EmployeeNotFoundException {
        return  new ResponseEntity<>(employeeDataService.getColumn(emailid,projectId),HttpStatus.OK);
    }


    //  http://localhost:9999/employee-details/v1/delete-column/{emailId}/{projectId}/{columnId}
  @DeleteMapping("/delete-column/{emailId}/{projectId}/{columnId}")
  public ResponseEntity<?> deleteTask(@PathVariable String emailId,@PathVariable String projectId,@PathVariable String columnId) throws EmployeeNotFoundException, ProjectNotFoundException, ColumnNotFoundException {
    return new ResponseEntity<>(employeeDataService.deleteColumn(emailId,projectId,columnId),HttpStatus.OK);
  }
}
