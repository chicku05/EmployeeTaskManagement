package com.employee.controller;

import com.employee.dto.EmployeeDTO;
import com.employee.dto.TaskDetailsDTO;
import com.employee.model.Task;
import com.employee.model.TaskStatus;
import com.employee.service.EmployeeTaskService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Validated
@RestController
@RequestMapping("/api")
@ApiResponses(value = {
        @ApiResponse(responseCode = "200",description = "OK")
})
public class EmployeeTaskManagementController {

    @Autowired
    private EmployeeTaskService service;

    @PostMapping("/employees")
    public ResponseEntity<EmployeeDTO> createEmployee(@Valid @RequestBody EmployeeDTO employee) { // @Valid triggers validation
        return new ResponseEntity<>(service.createEmployee(employee),HttpStatus.OK);
    }

    @PostMapping("/employees/{employeeId}/tasks")
    public ResponseEntity<TaskDetailsDTO> assignTask(@PathVariable Long employeeId, @RequestBody TaskDetailsDTO task) {
        return new ResponseEntity<>(service.assignTaskToEmployee(employeeId, task),HttpStatus.OK);
    }

    @GetMapping("/employees/{employeeId}/tasks")
    public ResponseEntity<List<TaskDetailsDTO>> getTasks(@PathVariable Long employeeId,
                                                         @RequestParam(required = false) TaskStatus status) {
        return new ResponseEntity<>(service.getEmployeeTasks(employeeId, status), HttpStatus.OK);
    }

    @PutMapping("/tasks/{taskId}/status")
    public Task updateTaskStatus(@PathVariable Long taskId, @RequestParam TaskStatus newStatus) {
        return service.updateTaskStatus(taskId, newStatus);
    }

    @GetMapping("/employees/statistics")
    public Map<String, Long> getCompletionStatistics() {
        return service.getEmployeeCompletionStats();
    }

}
