package com.example.controller;

import com.example.model.Employee;
import com.example.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("")
@CrossOrigin(allowCredentials = "true", maxAge = 3600)
public class LoginController {
    @Autowired
    private EmployeeService employeeService;

    @PostMapping(value = "/login")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public String login(@RequestParam("password") String password, @RequestParam("name") String name) {
        Employee employee=employeeService.findByNameAndPassword(name, password);
        return employee.getName();
    }
}

