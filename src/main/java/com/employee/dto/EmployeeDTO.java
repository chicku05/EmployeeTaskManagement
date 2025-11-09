package com.employee.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDTO implements Serializable {

    private String name;
    private String email;
    private String department;
    private List<TaskDTO> taskDtoList;
}
