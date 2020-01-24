package com.example.controller;

import com.example.auth.Employee;
import com.example.service.RoleService;
import com.example.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("employee")
@CrossOrigin(allowCredentials="true",maxAge = 3600)
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private RoleService roleService;
    @GetMapping(value = "/list")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public List<Employee> listEmployee(){
        return employeeService.listEmployee();
    }

    @PostMapping(value = "/add")
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    public Object addEmployee(@RequestParam("name") String name,@RequestParam("age") Integer age,@RequestParam("password") String password,@RequestParam("role_name") String role_name){
        Employee employee=new Employee();
        employee.setName(name);
        employee.setPassword(password);
        employee.setAge(age);
        employee.setRole(roleService.findByName(role_name));
        return employeeService.addEmployee(employee);
    }

    /*@GetMapping(value = "/get")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public Employee findById(@RequestParam("id") String id){
           return employeeService.findById(id);
    }*/

    @GetMapping(value = "/get")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public Employee findByName(@RequestParam("name") String name){
        return employeeService.findByName(name);
    }

    @ResponseBody
    @PostMapping(value = "/update")
    @ResponseStatus(HttpStatus.OK)
    public Employee updateEmployee(@RequestParam("id") String id,
                                 @RequestParam(value="role_name",required = false) String role_name,
                                 @RequestParam(value = "password",required = false) String password,
                                 @RequestParam(value = "name",required = false) String name,
                                 @RequestParam(value = "age",required = false) Integer age){
        Employee employee=new Employee();
        employee.setId(Integer.parseInt(id));
        if(name!=null) {
            employee.setName(name);
        }
        if(role_name!=null) {
            employee.setRole(roleService.findByName(role_name));
        }
        if(password!=null) {
            employee.setPassword(password);
        }
        if(age!=null) {
            employee.setAge(age);
        }
        return employeeService.updateEmployee(id,employee);
    }

    @ResponseBody
    @PostMapping(value = "/delete")
    @ResponseStatus(HttpStatus.OK)
    public void deleteEmployee(@RequestParam("id") String id){
        employeeService.deleteEmployee(id);
    }
}
