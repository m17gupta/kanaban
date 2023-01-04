package com.capstoneproject.taskdata.service;

import com.capstoneproject.taskdata.domain.*;
import com.capstoneproject.taskdata.exception.*;

import java.util.List;

public interface EmployeeDataService {

    public abstract EmployeeData addUser(CommonUser commonUser) throws EmployeeAlreadyExistsException;

    public  abstract EmployeeData addProject(String emailId, Project project) throws ProjectAlreadyExistsException,EmployeeNotFoundException;


    public abstract  List<Project> getAllProject(String name);
    public Boolean deleteProjectById(String userId, String projectId) throws ProjectNotFoundException,EmployeeNotFoundException;
    public abstract EmployeeData addtask(String emailid,String projectId,String colId,TaskData taskData)  throws ProjectNotFoundException,ColumnNotFoundException,EmployeeNotFoundException ,TasklimitExceed ,TaskIdAlreadyExistsException;
    public  abstract EmployeeData updateTask(String emailid,String projectId,String colId,TaskData taskData);

    public   abstract   boolean deleteTask(String emailid,String projectId,String colId,String taskId);

    public  abstract List<Column> addColumn(String emailId, String projectId ,Column column)throws ProjectNotFoundException,ColumnAlreadyExistsException;


    public abstract  List<Column> getColumn(String emailId, String projectId ) throws EmployeeNotFoundException,ProjectNotFoundException;
      public abstract   EmployeeData deleteColumn(String emailId, String projectId, String columnId) throws EmployeeNotFoundException,ProjectNotFoundException,ColumnNotFoundException ;

        public abstract  Project getProjectById(String email ,String projectId);



//    public  abstract List<TaskData> getTaskTeamMemeber(String projectId,String assigne);
    public abstract  EmployeeData getTeamMemberProject(String projectid);

//    public  abstract  TaskData getTaskById(String taskId);


//    public abstract boolean deleteTaskByName(String employeeName,TaskData taskData) throws EmployeeNotFoundException;
//    public abstract EmployeeData upateTask(String employeeName, TaskData taskData) throws EmployeeNotFoundException;
}
