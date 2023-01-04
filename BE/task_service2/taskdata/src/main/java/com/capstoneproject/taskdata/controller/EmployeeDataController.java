package com.capstoneproject.taskdata.controller;

import com.capstoneproject.taskdata.domain.*;
import com.capstoneproject.taskdata.exception.*;
import com.capstoneproject.taskdata.service.EmployeeDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employee-details/v1")
public class EmployeeDataController {
    @Autowired
    private EmployeeDataService employeeDataService;



//    
    @PostMapping("/add-user")
    public ResponseEntity<?> addUser(@RequestBody CommonUser commonUser) throws EmployeeAlreadyExistsException{
        try{
            return new ResponseEntity<>(employeeDataService.addUser(commonUser),HttpStatus.OK);
        } catch (EmployeeAlreadyExistsException e) {
            throw new EmployeeAlreadyExistsException();
        }
    }
      @PostMapping("/add-project/{emaild}")
      public ResponseEntity<?> addProjectDetail(@RequestBody Project project,@PathVariable  String emaild) throws ProjectAlreadyExistsException, EmployeeNotFoundException {
        return new ResponseEntity<>(employeeDataService.addProject(emaild,project),HttpStatus.OK);
      }



      @GetMapping("/get-all-project/{emailid}")
    public  ResponseEntity<?> getAllProject(@PathVariable String emailid){
        return new ResponseEntity<>(employeeDataService.getAllProject(emailid),HttpStatus.OK);

      }

    @GetMapping("/get-project-by-Id/{email}/{projectId}")
    public ResponseEntity<?> getProjectById(@PathVariable String email, @PathVariable String projectId){
        return  new ResponseEntity<>(employeeDataService.getProjectById(email,projectId),HttpStatus.OK);
    }


    @PostMapping("/add-task/{emailId}/{projectId}/{colId}")

    public ResponseEntity<?> addtask(@PathVariable String emailId,@PathVariable String projectId,@PathVariable String colId,@RequestBody TaskData taskData) throws ProjectNotFoundException, ColumnNotFoundException, EmployeeNotFoundException, TasklimitExceed, TaskIdAlreadyExistsException {
        try{
            EmployeeData e= employeeDataService.addtask(emailId,projectId,colId,taskData);
            return new ResponseEntity<>(e, HttpStatus.OK);
        } catch (ProjectNotFoundException ex){
             throw new ProjectNotFoundException();

        }
        catch(ColumnNotFoundException ex){
            throw new ColumnNotFoundException();
        }catch(TasklimitExceed ex){
            throw new TasklimitExceed();
        }catch(TaskIdAlreadyExistsException ex){
            throw  new TaskIdAlreadyExistsException();
        }


    }

    @PutMapping("/update-task/{emailid}/{projectId}/{colId}")
    public ResponseEntity<?> updateTask(@PathVariable String emailid,@PathVariable String projectId,@PathVariable String colId,@RequestBody TaskData taskData){
       return new ResponseEntity<>(employeeDataService.updateTask(emailid,projectId,colId,taskData),HttpStatus.OK);
    }
////    http://localhost:9999/employee-details/v1/task-list
@DeleteMapping("/delete-task/{emailid}/{projectId}/{colId}/{taskId}")
public ResponseEntity<?> deleteTask(@PathVariable String emailid,@PathVariable String projectId,@PathVariable String colId,@PathVariable String taskId){
    return new ResponseEntity<>(employeeDataService.deleteTask(emailid,projectId,colId,taskId),HttpStatus.OK);
}


    @DeleteMapping("delete-project/{emailId}/{projectId}")
    public ResponseEntity<?> deleteProject(@PathVariable String emailId,@PathVariable String projectId) throws ProjectNotFoundException, EmployeeNotFoundException {
        return new ResponseEntity<>(employeeDataService.deleteProjectById(emailId,projectId),HttpStatus.OK);
    }

    //http://localhost:9999/employee-details/v1/get-task-by-Id/task-id
//   @ GetMapping("/get-task-by-Id/{taskId}")
//        public ResponseEntity<?> getTaskById( @PathVariable String taskId){
//         return  new ResponseEntity<>(employeeDataService.getTaskById(taskId),HttpStatus.OK);
//        }


        //http://localhost:9999/employee-details/v1/get-all-columns/email/projectId
    @GetMapping("/get-all-columns/{emailId}/{projectId}")
    public ResponseEntity<?> getAllColumns(@PathVariable String emailId,@PathVariable String projectId) throws ProjectNotFoundException, EmployeeNotFoundException {
        return new ResponseEntity<>(employeeDataService.getColumn(emailId,projectId),HttpStatus.OK);
    }
//
////    http://localhost:9999/employee-details/v1/delete-task-by-name/Kavya
//    @DeleteMapping("/delete-task-by-name/{employeeName}")
//    public ResponseEntity<?> deleteTaskByName(@PathVariable String employeeName,@RequestBody TaskData taskData) throws EmployeeNotFoundException{
//        try{
//            employeeDataService.deleteTaskByName(employeeName,taskData);
//            return new ResponseEntity<>("task deleted suucesfully",HttpStatus.OK);
//        } catch (EmployeeNotFoundException e) {
//            throw new EmployeeNotFoundException();
//        }
//    }
//
////    http://localhost:9999/employee-details/v1/update-task/seetha
//    @PutMapping("/update-task/{employeeName}")
//    public ResponseEntity<?> updateTaskByName(@PathVariable String employeeName,@RequestBody TaskData taskData) throws EmployeeNotFoundException {
//        try{
//           EmployeeData update = employeeDataService.upateTask(employeeName,taskData);
//           return new ResponseEntity<>(update,HttpStatus.OK);
//        } catch (EmployeeNotFoundException e) {
//            throw new EmployeeNotFoundException();
//        }
//    }
}
