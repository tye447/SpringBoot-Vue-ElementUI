package com.example.controller;

import com.example.model.Employee;
import com.example.service.EmployeeService;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("employee")
@CrossOrigin(allowCredentials="true",maxAge = 3600)
public class EmployeeController {
    private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping(value = "/list")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public List<Employee> listEmployee(){
        return employeeService.listEmployee();
    }

    @PostMapping(value = "/add")
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    public Object addEmployee(@RequestParam("name") String name,@RequestParam("age") Integer age,@RequestParam("password") String password){
        Employee employee=new Employee();
        employee.setName(name);
        employee.setPassword(password);
        employee.setAge(age);
        return employeeService.addEmployee(employee);
    }
    @ResponseBody
    @PostMapping(value = "/update")
    @ResponseStatus(HttpStatus.OK)
    public Employee updateEmployee(@RequestParam("id") Integer id,
                                 //@RequestParam(value = "password",required = false) String password,
                                 @RequestParam(value = "name",required = false) String name,
                                 @RequestParam(value = "age",required = false) Integer age){
        Employee employee=new Employee();
        employee.setId(id);
        if(name!=null) {
            employee.setName(name);
        }
        //if(password!=null) {
          //  employee.setPassword(password);
        //}
        if(age!=null) {
            employee.setAge(age);
        }
        return employeeService.updateEmployee(id,employee);
    }

    @ResponseBody
    @PostMapping(value = "/delete")
    @ResponseStatus(HttpStatus.OK)
    public void deleteEmployee(@RequestParam("id") Integer id){
        employeeService.deleteEmployee(id);
    }

}
