package com.capstoneproject.taskdata.service;

import com.capstoneproject.taskdata.domain.Column;
import com.capstoneproject.taskdata.domain.EmployeeData;
import com.capstoneproject.taskdata.domain.Project;
import com.capstoneproject.taskdata.domain.TaskData;
import com.capstoneproject.taskdata.exception.EmployeeNotFoundException;
import com.capstoneproject.taskdata.repository.TeamMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TeamMemberServiceImpl implements TeamMemberService{


    @Autowired
    private TeamMemberRepository teamMemberRepository;





    @Override
    public Project getProject(String projectId,String assignee) throws EmployeeNotFoundException {
        List<EmployeeData> employeeDataList = teamMemberRepository.findAll();
        System.out.println("hello");
        System.out.println(employeeDataList);
        for (int i = 0; i < employeeDataList.size(); i++) {
            System.out.println(employeeDataList.get(i));
            if (employeeDataList.get(i) != null) {
                EmployeeData employeeData = employeeDataList.get(i);
                if (employeeData.getRole().equals("project manager")) {
                    List<Project> projectList = employeeData.getProjects();
                    if (projectList != null) {
                        Project project = projectList.stream().filter(p -> p.getProjectId().equalsIgnoreCase(projectId)).findAny().get();
                           if(project!=null){
                               List<Column> columnList = project.getColumnList();
                               if (columnList != null) {
                                   List<TaskData> taskDataList = columnList.get(0).getTaskList();
                                   if (taskDataList != null) {
                                       List<TaskData> taskData = taskDataList.stream().filter(p -> p.getAssigne().equals(assignee)).toList();
                                       columnList.get(0).setTaskList(taskData);
                                       project.setColumnList(columnList);
                                       return project;
                           }

                            }
                        }
                    }
                }
            }
        }
        return  null;
    }

    // update the task while drah and drop
    @Override
    public EmployeeData updateColumn(String emailId, String projectId, String currentColumn, String previousColumn,TaskData taskData) {
        if (teamMemberRepository.findById(emailId).isPresent()) {

            String  taskId=taskData.getTaskId();
            EmployeeData employeeData = teamMemberRepository.findById(emailId).get();
            List<Project> projectList = employeeData.getProjects();
            Project project = projectList.stream().filter(p -> p.getProjectId().equals(projectId)).findAny().get();
            List<Column> columnList = project.getColumnList();
            Column col = columnList.stream().filter(c -> c.getColumnId().equals(previousColumn)).findAny().get();
            List<TaskData> taskDataList1 = col.getTaskList();
            boolean taskDeleted=taskDataList1.removeIf(t -> t.getTaskId().equals(taskId));
            col.setTaskList(taskDataList1);
            if(taskDeleted=true) {
                Column column = columnList.stream().filter(c -> c.getColumnId().equals(currentColumn)).findAny().get();
                List<TaskData> taskDataList = column.getTaskList();
                taskDataList.add(taskData);
                column.setTaskList(taskDataList);
                return teamMemberRepository.save(employeeData);
            }
        }
        return null;
    }


     @Override
     public TaskData getTaskById(String emailId, String projectId, String columnId, String taskId) {
         if (teamMemberRepository.findById(emailId).isPresent()) {
             EmployeeData employeeData = teamMemberRepository.findById(emailId).get();
             List<Project> projectList = employeeData.getProjects();
             Project project = projectList.stream().filter(p -> p.getProjectId().equals(projectId)).findAny().get();
             List<Column> columnList = project.getColumnList();
             Column column = columnList.stream().filter(c -> c.getColumnId().equals(columnId)).findAny().get();
             List<TaskData> taskDataList = column.getTaskList();
             TaskData taskData = taskDataList.stream().filter(t -> t.getTaskId().equals(taskId)).findAny().get();
             return taskData;
         }
         return null;
     }

      @Override
      public EmployeeData getProjectManagerMailId(String projectId){
          List<EmployeeData>employeeDataList=teamMemberRepository.findAll();
          for( int i=0; i<employeeDataList.size();i++) {
              EmployeeData employee = employeeDataList.get(i);
              if (employee.getRole().equals("project manager")) {
                  List<Project> projectList = employee.getProjects().stream().filter(p -> p.getProjectId().equalsIgnoreCase(projectId)).toList();
                  if (projectList != null) {
                      return employee;
                  }
              }
          }

          return  null;
      }


}


