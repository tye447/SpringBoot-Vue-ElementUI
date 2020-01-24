package com.example.controller;

import com.example.auth.Role;
import com.example.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("auth/role")
@CrossOrigin(allowCredentials="true",maxAge = 3600)
public class RoleController {
    @Autowired
    private RoleService roleService;
    @Autowired
    @GetMapping(value = "/list")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public List<Role> roleList(){
        return roleService.listRole();
    }

    @PostMapping(value = "/add")
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    public Object addRole(@RequestParam("name") String name,
                          @RequestParam("permission") String permission
                          ){
        Role role=new Role();
        role.setName(name);
        role.setPermission(permission);
        return roleService.addRole(role);
    }

    @ResponseBody
    @PostMapping(value = "/update")
    @ResponseStatus(HttpStatus.OK)
    public Role updateRole(@RequestParam("id") String id,
                           @RequestParam(value = "name",required = false) String name,
                           @RequestParam(value = "permission",required = false) String permission){
        Role role=new Role();
        role.setId(Integer.parseInt(id));
        if(name!=null) {
            role.setName(name);
        }
        if(permission!=null) {
            role.setPermission(permission);
        }
       return roleService.updateRole(id,role);
    }

    @ResponseBody
    @DeleteMapping(value = "/delete")
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void deleteRole(@RequestParam("id") String id){
        roleService.deleteRole(id);
    }
}
