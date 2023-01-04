package com.capstoneproject.taskdata.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;


import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Project {
    @Id
    private String projectId;
    private String projectName;
    private String duration;
    List<Column> columnList;
}
