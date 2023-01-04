package com.capstoneproject.taskdata.controller;

import com.capstoneproject.taskdata.domain.Project;
import com.capstoneproject.taskdata.domain.TaskData;
import com.capstoneproject.taskdata.exception.EmployeeNotFoundException;
import com.capstoneproject.taskdata.service.EmployeeDataService;
import com.capstoneproject.taskdata.service.TeamMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employee-details/v1")
public class TeamMemberController {
    @Autowired
    private EmployeeDataService employeeDataService;
    @Autowired
    private TeamMemberService teamMemberService;

    // http://localhost:9999/employee-details/v1/team-member-project/{projectId}
    @GetMapping("/team-member-project/{projectId}/{assignee}")
     public ResponseEntity<?> getTeamMemberProject(@PathVariable String projectId,@PathVariable String assignee) throws EmployeeNotFoundException {
        return  new ResponseEntity<>(teamMemberService.getProject (projectId,assignee),HttpStatus.OK);
    }

    //http://localhost:9999/employee-details/v1/update-column/{emailId}/{projectId}/{currentColumn}/{previousColumn}"

    @PutMapping("/update-column/{emailId}/{projectId}/{currentColumn}/{previousColumn}")
    public ResponseEntity<?> updateColumn(@PathVariable String emailId,@PathVariable String projectId,@PathVariable String currentColumn,@PathVariable String previousColumn,@RequestBody TaskData taskData){
        return new ResponseEntity<>(teamMemberService.updateColumn(emailId,projectId,currentColumn,previousColumn,taskData),HttpStatus.OK);
    }


    //http://localhost:9999/employee-details/v1/get-task-by-Id/task-id
    @GetMapping("/get-task-by-Id/{emailId}/{projectId}/{columnId}/{taskId}")
    public ResponseEntity<?> getTaskById( @PathVariable String emailId,@PathVariable String projectId,@PathVariable String columnId,@PathVariable String taskId){
        return  new ResponseEntity<>(teamMemberService.getTaskById(emailId,projectId,columnId,taskId),HttpStatus.OK);
    }


    //http://localhost:9999/employee-details/v1/get-project-manager-emailid/projectid
    @GetMapping("/get-project-manager-emailid/{projectId}")
    public  ResponseEntity<?> getProjectManagerEmail(@PathVariable String projectId){
        return  new ResponseEntity<>(teamMemberService.getProjectManagerMailId(projectId),HttpStatus.OK);
    }
}
