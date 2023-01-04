package com.capstoneproject.taskdata.service;

import com.capstoneproject.taskdata.domain.Column;
import com.capstoneproject.taskdata.domain.EmployeeData;
import com.capstoneproject.taskdata.domain.Project;
import com.capstoneproject.taskdata.domain.TaskData;
import com.capstoneproject.taskdata.exception.EmployeeNotFoundException;

import java.util.ArrayList;

import java.util.List;

public interface TeamMemberService {

    public abstract Project getProject(String projectId ,String assignee) throws EmployeeNotFoundException;

    EmployeeData updateColumn(String emailId, String projectId, String currentColumn, String previousColumn,TaskData taskData);

    public abstract TaskData getTaskById(String emailId, String projectId, String columnId, String taskId);

    public  abstract  EmployeeData getProjectManagerMailId(String projectId);


}
