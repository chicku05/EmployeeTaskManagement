package com.employee.serviceImpl;

import com.employee.dto.EmployeeDTO;
import com.employee.dto.TaskDTO;
import com.employee.dto.TaskDetailsDTO;
import com.employee.model.Employee;
import com.employee.model.Task;
import com.employee.model.TaskStatus;
import com.employee.repository.EmployeeRepository;
import com.employee.repository.TaskRepository;
import com.employee.service.EmployeeTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class EmployeeTaskServiceImpl implements EmployeeTaskService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private TaskRepository taskRepository;

    @Override
    public EmployeeDTO createEmployee(EmployeeDTO employee) {
        if (employeeRepository.findByEmail(employee.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Email already in use: " + employee.getEmail());
        }
        Employee emp = employeeRepository.save(convertDtoToEntity(employee));
        return convertEntityToDto(emp);
    }

    @Override
    public TaskDetailsDTO assignTaskToEmployee(Long employeeId, TaskDetailsDTO task) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new NoSuchElementException("Employee not found"));
         Task entityOperation = taskRepository.save(mappingRequest(task,employee));
        return convertEntityToDto(entityOperation) ;
    }

    @Override
    public List<TaskDetailsDTO> getEmployeeTasks(Long employeeId, TaskStatus status) {
        List<Task> task = null;
        if (status == null) {
            task = taskRepository.findByEmployeeId(employeeId);
            return convertEntityToDto(task);
        } else {
            task = taskRepository.findByEmployeeIdAndStatus(employeeId, status);
            return convertEntityToDto(task);
        }
    }

    @Override
    public Task updateTaskStatus(Long taskId, TaskStatus newStatus) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new NoSuchElementException("Task not found"));
        task.setStatus(newStatus);
        return taskRepository.save(task);
    }

    @Override
    public Map<String, Long> getEmployeeCompletionStats() {
        List<Employee> employees = employeeRepository.findAll();

        return employees.stream()
                .collect(Collectors.toMap(
                        Employee::getName,
                        employee -> employee.getTasks().stream()
                                .filter(task -> task.getStatus() == TaskStatus.COMPLETED)
                                .count()
                ));
    }

    private Employee convertDtoToEntity(EmployeeDTO employee){
        Employee emp = Employee.builder()
                .name(employee.getName())
                .email(employee.getEmail())
                .department(employee.getDepartment())
                .build();
        List<Task> task = new ArrayList<>();
        task = employee.getTaskDtoList().stream().map(mapTask->
                Task.builder().title(mapTask.getTitle()).description(mapTask.getDescription()).status(TaskStatus.valueOf(mapTask.getStatus())).employee(emp)
                        .build()).toList();

         emp.setTasks(task);
        return emp;
    }

    private EmployeeDTO convertEntityToDto(Employee employee){
        EmployeeDTO emp = EmployeeDTO.builder()
                .name(employee.getName())
                .email(employee.getEmail())
                .department(employee.getDepartment())
                .build();
        List<TaskDTO> taskDto = new ArrayList<>();
        taskDto = employee.getTasks().stream().map(mapTask->
                TaskDTO.builder().title(mapTask.getTitle()).description(mapTask.getDescription()).status(mapTask.getStatus().name()).employeeId(mapTask.getId())
                        .build()).toList();
        emp.setTaskDtoList(taskDto);
        return emp;
    }

    private List<TaskDetailsDTO> convertEntityToDto(List<Task> task) {
        return task.stream().map(dto ->
                TaskDetailsDTO.builder().title(dto.getTitle()).description(dto.getDescription()).status(dto.getStatus().name())
                        .employeeId(dto.getId()).build()).toList();
    }

    private Task mappingRequest(TaskDetailsDTO task, Employee employee){
        return Task.builder()
                .title(task.getTitle())
                .description(task.getDescription())
                .status(TaskStatus.valueOf(task.getStatus()))
                .employee(employee)
                .build();
    }


    private TaskDetailsDTO convertEntityToDto(Task task){

        return  TaskDetailsDTO.builder().title(task.getTitle()).description(task.getDescription()).status(task.getStatus().name()).employeeId(task.getId()).build();
    }
}
