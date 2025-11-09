package com.employee.repository;

import com.employee.dto.EmployeeDTO;
import com.employee.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long> {
    @Query("SELECT e.name, e.email, e.department FROM Employee e")
    Optional<EmployeeDTO> findByEmail(String email);
}
