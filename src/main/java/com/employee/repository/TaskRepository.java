package com.employee.repository;

import com.employee.model.Task;
import com.employee.model.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task,Long> {

    List<Task> findByEmployeeIdAndStatus(Long employeeId, TaskStatus status);
    List<Task> findByEmployeeId(Long employeeId);
}
