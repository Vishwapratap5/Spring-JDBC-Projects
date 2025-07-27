package com.guru.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component
@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Task {
    private int taskID;
    private String taskName;
    private String taskDescription;
    private String taskPriority;
    private Timestamp taskDueDate;//[Format : "2025-08-01 18:00:00" ]
    private String taskStatus;

}
