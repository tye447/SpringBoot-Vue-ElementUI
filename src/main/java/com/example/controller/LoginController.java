package com.example.controller;

import com.example.common.ResultCode;
import com.example.model.*;
import com.example.service.EmployeeService;
import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("login")
@CrossOrigin(allowCredentials="true",maxAge = 3600)
public class LoginController {
    @Autowired
    private EmployeeService employeeService;
    @PostMapping(value = "")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public String login(@RequestParam("password") String password, @RequestParam("name") String name){
        return employeeService.findByNameAndPassword(name, password).getName();
    }
}

