package com.example.controller;

import com.example.auth.Employee;
import com.example.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("login")
@CrossOrigin(allowCredentials="true",maxAge = 3600)
public class LoginController {
    @Autowired
    private EmployeeService employeeService;
    @PostMapping(value = "")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public Object login(@RequestParam("password") String password,@RequestParam("name") String name){
        System.out.println(password);
        Employee employee=employeeService.findByNameAndPassword(name,password);
        System.out.println(employee.getAge());
        return employee;
    }
}

