package com.capstoneproject.taskdata.taskdata;

import com.capstoneproject.taskdata.domain.Column;
import com.capstoneproject.taskdata.domain.EmployeeData;
import com.capstoneproject.taskdata.domain.Project;
import com.capstoneproject.taskdata.domain.TaskData;
import com.capstoneproject.taskdata.exception.ColumnNotFoundException;
import com.capstoneproject.taskdata.exception.EmployeeNotFoundException;
import com.capstoneproject.taskdata.exception.ProjectNotFoundException;
import com.capstoneproject.taskdata.exception.TaskIdNotFoundException;
import com.capstoneproject.taskdata.repository.TeamMemberRepository;
import com.capstoneproject.taskdata.service.TeamMemberService;
import com.capstoneproject.taskdata.service.TeamMemberServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TeamMemberServiceTesting {
    @Mock
    private TeamMemberRepository teamMemberRepository;
    @Mock
    private TeamMemberService teamMemberService;
    @InjectMocks
    private TeamMemberServiceImpl teamMemberServiceImpl;

    private EmployeeData employeeData;
    private Project project;
    private Column column;
    private TaskData taskData;

    @BeforeEach
    public void init(){
        List<Project> projectList=new ArrayList<>();
        employeeData=new EmployeeData("raju@gmail.com","raju","Team member",projectList);
        List<Column>columnList=new ArrayList<>();
        project =new Project("P10","Electrical","2 months",columnList);
        List<TaskData> taskDataList=new ArrayList<>();
        column=new Column("m01","todo",taskDataList);
        taskData=new TaskData("n10","today","transforms","raju","high","pending");
    }
    @AfterEach
    public void clear(){
        employeeData=null;
        column=null;
        taskData=null;
    }
    @Test
    public void getTaskByIdSuccess() throws ProjectNotFoundException, ColumnNotFoundException, TaskIdNotFoundException, EmployeeNotFoundException {
       when(teamMemberRepository.findById(employeeData.getEmailId())).thenReturn(Optional.ofNullable(employeeData));
       employeeData.getProjects().add(project);
       project.getColumnList().add(column);
       column.getTaskList().add(taskData);
       doReturn(taskData).when(teamMemberService).getTaskById(employeeData.getEmailId(),project.getProjectId(),column.getColumnId(),taskData.getTaskId());
    }
}
