package com.capstoneproject.taskdata.taskdata;

import com.capstoneproject.taskdata.domain.CommonUser;
import com.capstoneproject.taskdata.domain.EmployeeData;
import com.capstoneproject.taskdata.domain.Project;
import com.capstoneproject.taskdata.exception.EmployeeAlreadyExistsException;
import com.capstoneproject.taskdata.proxy.EmployeeProxy;
import com.capstoneproject.taskdata.repository.EmployeeDataRepository;
import com.capstoneproject.taskdata.service.EmployeeDataServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTesting {

    @Mock
    private EmployeeProxy employeeProxy;
    @Mock
    private EmployeeDataRepository employeeDataRepository;

    @InjectMocks
    private EmployeeDataServiceImpl employeeDataServiceImpl;

    private EmployeeData employeeData;
    private CommonUser commonUser;
    private Project project;

    @BeforeEach
    public void init(){
        List<Project> projectList= new ArrayList<>();
        employeeData =new EmployeeData("rani@gmail.com","rani","team member",projectList);
        commonUser =new CommonUser("rani","team member","rani@gmail.com","abc");
    }
    @AfterEach
    public void clear(){
        employeeData=null;
        project=null;
    }
    @Test
    public void addUserSuccess() throws EmployeeAlreadyExistsException{
        when(employeeDataRepository.findById(employeeData.getEmployeeName())).thenReturn(Optional.ofNullable(null));
        when(employeeDataRepository.insert(employeeData)).thenReturn(employeeData);
        assertEquals(employeeData,employeeDataServiceImpl.addUser(commonUser));

        verify(employeeDataRepository,times(1)).findById(employeeData.getEmployeeName());
        verify(employeeDataRepository,times(1)).insert(employeeData);
    }

    @Test
    public void addUserFailure(){
        when(employeeDataRepository.findById(employeeData.getEmployeeName())).thenReturn(Optional.ofNullable(employeeData));
        assertThrows(EmployeeAlreadyExistsException.class,()->employeeDataServiceImpl.addUser(commonUser));
        verify(employeeDataRepository,times(1)).findById(employeeData.getEmployeeName());
    }

}
