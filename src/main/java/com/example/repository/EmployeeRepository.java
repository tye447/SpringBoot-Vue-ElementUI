package com.example.repository;
import com.example.auth.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, String> {
    Employee findByNameAndPassword(String name, String password);
    Employee findByName(String name);
    Employee findByPassword(String password);
}

