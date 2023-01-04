package com.capstoneproject.taskdata.taskdata;

import com.capstoneproject.taskdata.domain.*;
import com.capstoneproject.taskdata.repository.EmployeeDataRepository;
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
public class EmployeeDataReprositoryTest {
    @Autowired
    private EmployeeDataRepository employeeDataRepository;

    private CommonUser commonUser;

    private EmployeeData employeeData;

    private Project project;

    private Column column;

    private TaskData taskData;

    @BeforeEach
    public void setUp(){
        commonUser = new CommonUser("ritesh","project manager","ritesh@gmail.com","abc");
        List<Project> projectList=new ArrayList<>();
        employeeData =new EmployeeData("ritesh@gmail.com","ritesh","project manager",projectList);
        List<Column>columnList=new ArrayList<>();
        project=new Project("F10","Latest","2 years",columnList);
        List<TaskData> taskDataList=new ArrayList<>();
        column=new Column("F11","to-do",taskDataList);
        taskData=new TaskData("F12","20-2-2022","new project","ritesh","high","completed");
    }
    @AfterEach
    public void clean(){
        employeeData=null;
        project=null;
        column=null;
        taskData=null;
        employeeDataRepository.deleteById("ritesh@gmail.com");
    }
    @Test
    public void addUser(){
        employeeDataRepository.insert(employeeData);
        List<EmployeeData> employeeDataList=employeeDataRepository.findAll();
        assertEquals(8,employeeDataList.size());
    }
    @Test
    public void addProject(){
        employeeDataRepository.insert(employeeData);
        employeeData.getProjects().add(project);
        assertEquals(1,employeeData.getProjects().size());
    }
    @Test
    public void addColumn(){
        employeeDataRepository.insert(employeeData);
        employeeData.getProjects().add(project);
        Project p1=employeeData.getProjects().get(0);
        p1.getColumnList().add(column);
        assertEquals(1,p1.getColumnList().size());
    }

}
