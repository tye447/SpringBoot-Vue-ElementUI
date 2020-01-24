package com.example.service;

import com.example.auth.Employee;
import com.example.repository.EmployeeRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.util.Util.getNullPropertyNames;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;
    public List<Employee> listEmployee(){
        return employeeRepository.findAll();
    }
    public Employee addEmployee(Employee employee){
        return employeeRepository.save(employee);
    }
    public void deleteEmployee(String id){
        employeeRepository.deleteById(id);
    }
    public Employee updateEmployee(String id,Employee employee){
        Employee currentInstance=employeeRepository.findById(id).orElse(null);
        String[] nullPropertyNames = getNullPropertyNames(employee);
        BeanUtils.copyProperties(employee, currentInstance, nullPropertyNames);
        return employeeRepository.save(currentInstance);
    }
    public Employee findById(String id){
        Employee employee = employeeRepository.findById(id).orElse(null);
        return employee;
    }
    public Employee findByNameAndPassword(String name,String password){
        return employeeRepository.findByNameAndPassword(name,password);
    }
    public Employee findByName(String name){
        return employeeRepository.findByName(name);
    }

}
