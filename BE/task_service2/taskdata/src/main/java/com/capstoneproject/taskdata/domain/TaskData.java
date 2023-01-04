package com.capstoneproject.taskdata.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskData {
      @Id
      private String taskId;
    private String deadline;
    private String title,assigne,priority,status;

}
