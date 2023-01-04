package com.capstoneproject.taskdata.taskdata;

import com.capstoneproject.taskdata.controller.EmployeeDataController;
import com.capstoneproject.taskdata.domain.CommonUser;
import com.capstoneproject.taskdata.domain.EmployeeData;
import com.capstoneproject.taskdata.domain.Project;
import com.capstoneproject.taskdata.exception.EmployeeAlreadyExistsException;
import com.capstoneproject.taskdata.exception.EmployeeNotFoundException;
import com.capstoneproject.taskdata.exception.ProjectAlreadyExistsException;
import com.capstoneproject.taskdata.service.EmployeeDataService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class EmployeeControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private EmployeeDataService employeeDataService;

    @InjectMocks
    private EmployeeDataController employeeDataController;

    private EmployeeData employeeData;
    private CommonUser commonUser;
    private Project project;

    @BeforeEach
    public void init(){
        project=new Project("H1","Hystrix","1 year",new ArrayList<>());
       commonUser=new CommonUser("pal","project manager","pal@gmail.com","abc");
       employeeData=new EmployeeData("pal@gmail.com","pal","project manager",new ArrayList<>());
        mockMvc= MockMvcBuilders.standaloneSetup(employeeDataController).build();
    }
    @AfterEach
    public void setUp(){
        commonUser =null;
    }

    private static String convertToJson(final Object obj){
        String result="";
        try{
            ObjectMapper mapper =new ObjectMapper();
            result =mapper.writeValueAsString(obj);
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        return result;
    }
    @Test
    public void addEmployeeSuccess() throws Exception {
        when(employeeDataService.addUser(commonUser)).thenReturn(employeeData);

        mockMvc.perform(post("/employee-details/v1/add-user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(convertToJson(commonUser)))
                .andExpect(status().isOk()).andDo(MockMvcResultHandlers.print());
        verify(employeeDataService,times(1)).addUser(commonUser);

    }
    @Test
    public void addEmployeeFailure() throws Exception {
        when(employeeDataService.addUser(commonUser)).thenThrow(EmployeeAlreadyExistsException.class);

        mockMvc.perform(post("/employee-details/v1/add-user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(convertToJson(commonUser)))
                .andExpect(status().isConflict()).andDo(MockMvcResultHandlers.print());
        verify(employeeDataService,times(1)).addUser(commonUser);
    }
    @Test
    public void addProjectSuccess() throws Exception {
        when(employeeDataService.addProject(employeeData.getEmailId(),project)).thenReturn(employeeData);

        mockMvc.perform(post("/employee-details/v1/add-project/{emaild}",employeeData.getEmailId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(convertToJson(project)))
                .andExpect(status().isOk()).andDo(MockMvcResultHandlers.print());
        verify(employeeDataService,times(1)).addProject(employeeData.getEmailId(),project);
    }
    @Test
    public void addProjectFailure() throws Exception {
        when(employeeDataService.addProject(employeeData.getEmailId(),project)).thenThrow(ProjectAlreadyExistsException.class);

        mockMvc.perform(post("/employee-details/v1/add-project/{emaild}",employeeData.getEmailId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(convertToJson(project)))
                .andExpect(status().isConflict()).andDo(MockMvcResultHandlers.print());


    }
}
