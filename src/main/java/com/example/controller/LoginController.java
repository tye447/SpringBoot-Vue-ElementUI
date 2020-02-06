package com.example.controller;

import com.example.common.RestResult;
import com.example.common.ResultCode;
import com.example.common.ResultGenerator;
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
    @Autowired
    private ResultGenerator resultGenerator;

    @PostMapping(value = "/login")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public RestResult login(@RequestParam("password") String password, @RequestParam("name") String name) {
        try{
            Employee employee=employeeService.findByNameAndPassword(name, password);
            return resultGenerator.getSuccessResult(employee);
        }catch (Exception e){
            return resultGenerator.getFailResult(e.getMessage());
        }
    }
}

