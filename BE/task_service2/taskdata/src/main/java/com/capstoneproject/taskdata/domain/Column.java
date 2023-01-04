package com.capstoneproject.taskdata.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Column {
   @Id
//   @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String columnId;
//            private String columnName;
            List<TaskData> TaskList;
}
