package com.employee.dto;

import com.employee.model.TaskStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TaskDTO implements Serializable {

    private String title;
    private String description;
    private String status;
    private Long employeeId;
}
