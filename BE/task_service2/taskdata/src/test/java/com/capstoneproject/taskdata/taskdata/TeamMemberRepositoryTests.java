package com.capstoneproject.taskdata.taskdata;

import com.capstoneproject.taskdata.domain.Column;
import com.capstoneproject.taskdata.domain.EmployeeData;
import com.capstoneproject.taskdata.domain.Project;
import com.capstoneproject.taskdata.domain.TaskData;
import com.capstoneproject.taskdata.repository.TeamMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@DataMongoTest
public class TeamMemberRepositoryTests {
    @Autowired
    private TeamMemberRepository teamMemberRepository;

    private EmployeeData employeeData;

    private Project project;

    private Column column;

    private TaskData taskData;

    @BeforeEach
    public void setUp(){
        List<Project> projectList=new ArrayList<>();
        employeeData=new EmployeeData("rishi@gmail.com","rishi","team member",projectList);
        List<Column> columnList=new ArrayList<>();
        project=new Project("h1","hystrix","2 years",columnList);
        List<TaskData> taskDataList=new ArrayList<>();
        column=new Column("p1","processed",taskDataList);
        taskData=new TaskData("g1","20-2-2022","circuit breaker","rishi","medium","completed");
    }
    @AfterEach
    public void inIt(){
        project=null;
        column=null;
        taskData=null;
      teamMemberRepository.deleteById("rishi@gmail.com");
    }
    @Test
    public void getTaskById(){
        teamMemberRepository.insert(employeeData);
        employeeData.getProjects().add(project);
        project.getColumnList().add(column);
        column.getTaskList().add(taskData);
        assertEquals("g1",column.getTaskList().get(0).getTaskId());
    }
}
