package com.capstoneproject.taskdata.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document
public class EmployeeData {
    @Id
    private String emailId;
    private String employeeName;
    private String role;

   List<Project>projects;

}
