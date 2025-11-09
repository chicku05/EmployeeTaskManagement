package com.employee.service;

import com.employee.dto.EmployeeDTO;
import com.employee.dto.TaskDetailsDTO;
import com.employee.model.Task;
import com.employee.model.TaskStatus;

import java.util.List;
import java.util.Map;

public interface EmployeeTaskService {

    public EmployeeDTO createEmployee(EmployeeDTO employee);
    public TaskDetailsDTO assignTaskToEmployee(Long employeeId, TaskDetailsDTO task);
    public List<TaskDetailsDTO> getEmployeeTasks(Long employeeId, TaskStatus status);
    public Task updateTaskStatus(Long taskId, TaskStatus newStatus);
    public Map<String, Long> getEmployeeCompletionStats();
}
